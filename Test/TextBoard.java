package project.Test;

import project.Content.*;
import project.ColorModel.*;
import project.InBound.Boundable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//画板
class WritePanel extends JPanel {
    private Graphics2D g;
    private ArrayList<ChineseWords> text = new ArrayList<>();

    private void doWriting(Graphics g) {
        //graphics2D相当于画笔
        //这个是在弹出的窗口里面显示
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform atf = new AffineTransform();//一个映射，旋转角度的时候要用到

        //背景色携带者不用画出来，所以从 i=1 开始
        for (int i = 0; i < text.size(); i++){
            FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(text.get(i).getFont());
            //所有要画的东西都是从ChineseWords里面提取的，，那里面已经定义好了

            //画字体
            g2d.setFont(text.get(i).getFont());

            //画颜色
            g2d.setColor(text.get(i).getColor());

            //画角度
            g2d.setTransform(atf);//如果没有这个它就开始乱转
            g2d.rotate(text.get(i).getAngle()*Math.PI/180.0,text.get(i).getPosition().x,text.get(i).getPosition().y);

            //真正开始画
            g2d.drawString(text.get(i).getChinese(), text.get(i).getPosition().x, text.get(i).getPosition().y - fm.getLeading());
        }
    }

    public void addText(ChineseWords cw) {
        text.add(cw);
    }

    public ArrayList<ChineseWords> getText() { return text; }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doWriting(g);
    }
}

//框架
public class TextBoard extends JFrame {
    private WritePanel board;

    public TextBoard(int width, int height, Color col) {
        //The title
        setTitle("Word Cloud - Group 4: 隋晓地，钟瑞娟，王冬青");
        board = new WritePanel();

        // Put the WritePanel into the main window
        add(board);

        // Set its background  color
        board.setOpaque(true);//不透明
        board.setBackground(col);

        // Geometry of the main window
        setSize(width, height);
        setLocationRelativeTo(null);//设置窗口相对于指定组件的位置,null表示将窗口显示在屏幕中央

        // When users close the window, we quit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//结束窗口所在的应用程序。在窗口被关闭的时候会退出JVM
        setVisible(false);
    }

    //因为我的方法里只涉及到这一种，，所以其他的就没写,字体大小是从数组中提取的
    public void write(ChineseWords cw, int fontSize, String colorModelName, Boundable bound) {
        cw.setRandomAngle();
        cw.setFont("宋体", fontSize);

        //判断是否碰撞。。把之前生成的汉词都在text里面，检查碰撞的时候例遍数组
        boolean isCollide;
        do {
            //重新设置随机的初始位置
            cw.setRandomAngle();
            cw.setRandomPositionInBound(bound);
            isCollide = false;
            if (board.getText().size() > 0) {
                for (int j = 0; j < board.getText().size(); j++) {
                    if (cw.collide(board.getText().get(j))) {//cw.getRec().intersects(board.getText().get(j).getRec())
                        isCollide = true;
                    }
                    if (cw.getCount() > 1000){
                        j = board.getText().size() - 1;//用来退出循环
                    }
                }
            }
        }while (isCollide);
        MyColorModel c = new MyColorModel();
        cw.setColor(c.getOneColorOfModel(colorModelName));
        //如果放不下就不在text里再加东西
        if (cw.getCount() <= 1000) {
            board.addText(cw);
        }

    }

    public void display() {
        this.setVisible(true);
    }

    public void saveWordCloud(){
        Container container = this.getContentPane();
        BufferedImage bi = new BufferedImage(this.getWidth(),
                this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        container.printAll(g2d);
        try {
            //用当前时间的毫秒数作为文件名
            ImageIO.write(bi, "JPEG", new File("C:\\Users\\91426\\" +
                    "Downloads\\aaa\\Java\\project\\word-cloud\\" + System.currentTimeMillis() + ".JPG"));
        }catch (IOException ie){
            ie.printStackTrace();
        }
        g2d.dispose();
    }
}
