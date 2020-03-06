package project.Content;

import project.ColliCheck.Colliable;
import project.ColliCheck.ColliChecker;
import project.InBound.Boundable;

import java.awt.*;

public class ChineseWords implements Colliable{
    private String Chinese;
    private Font font;
    private double angle;
    private Color color;
    private Point startPosition;
    private int count = 0;//用来数碰撞的次数，如果超过1000次就自动退出


    //通过一个字符串来初始化一个ChineseWords
    public ChineseWords(String ch) {
        Chinese = ch;
        font = null;
        angle = 0;
        color = null;
        startPosition = new Point(600,400);
    }

    //set font
    public void setFont(String fontName, int fontSize){
        font = new Font(fontName,Font.BOLD,fontSize);
    }

    //set random angle 我觉得对于汉字来说只有横的和竖的感觉会更好看些，也比较好实现
    public void setRandomAngle(){
        int i = (int)(Math.random()*2);
        this.angle = 90 * i;
    }

    //set color
    public void setColor(Color c){
        color = c;
    }

    //set random start position in a bound
    public void setRandomPositionInBound(Boundable bound){
        boolean isIn;
        //需要四个点都在边界内，分水平和竖直两种情况检查，因为他们对应的坐标表达方式不同
        do {
            int x = (int)(Math.random()*1250);
            int y = (int)(Math.random()*850);
            if (this.getAngle() == 0) {//水平的情况
                if (bound.CheckInBound(x, y)
                        && bound.CheckInBound(x + this.getWidth(), y)
                        && bound.CheckInBound(x, y - this.getHeight())
                        && bound.CheckInBound(x + this.getWidth(), y - this.getHeight())) {
                    startPosition = new Point(x, y);
                    isIn = true;
                } else {
                    isIn = false;
                    count++;
                    if (count > 1000)
                        isIn = true;
                }
            } else{//竖直的情况
                if (bound.CheckInBound(x, y)
                        && bound.CheckInBound(x + this.getWidth(), y)
                        && bound.CheckInBound(x, y + this.getHeight())
                        && bound.CheckInBound(x + this.getWidth(), y + this.getHeight())) {
                    startPosition = new Point(x, y);
                    isIn = true;
                } else {
                    isIn = false;
                    count++;
                    if (count > 1000)
                        isIn = true;
                }
            }
        }while (!isIn);
    }

    //get Chinese
    public String getChinese(){
        return Chinese;
    }

    //get font
    public Font getFont() { return font; }

    //get angle
    @Override
    public double getAngle() { return this.angle; }

    //get color
    public Color getColor() { return color; }

    @Override
    public Point getPosition() {
        return startPosition;
    }

    //get width and height of the Character
    //汉字是方方正正的，以它的字体大小作为它的边长，就可以很容易得到一个词语的宽度和高度，而且都是整数
    //这里只涉及旋转角度是90度的倍数
    private int getWidth(){
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int width;
        if (this.getAngle() == 0)
        width = font.getSize() * Chinese.length() + fm.getLeading();//每一个字体大小都包括一个leading长度
        else
            width = font.getSize();
        return width;
    }

    private int getHeight(){
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        int height;
        if (this.getAngle() == 0)
        height = font.getSize();
        else
            height = font.getSize() * Chinese.length() + fm.getLeading();
        return height;
    }

    public int getCount(){
        return count;
    }

    //这个是连接 Colliable 与 getWidth、getHeight 的桥梁
    @Override
    public Dimension getDimension() {
        return new Dimension(this.getWidth(), this.getHeight());
    }

    @Override
    public boolean collide(Colliable colliable) {
        ColliChecker ccc = new ColliChecker();
        return ccc.collide(this, colliable);
    }
}
