package project.InBound;

//这个图形很容易出现词太多装不下去的情况
public class Star implements Boundable{
    private int centerX;
    private int centerY;
    private int radius;

    public Star(int a){
        radius = a;
        //这里设置成定值因为我的窗口大小要固定成1200和900
        centerX = 650;
        centerY = 420;
    }

    @Override
    public void setRadius(int a){
        radius = a;
    }

    @Override
    public int getRadius(){
        return radius;
    }

    //use the function (x-a)^2/3 + (y-b)^2/3 = a^2/3
    @Override
    public boolean CheckInBound(int x, int y){
        //如果没有取绝对值，复数是不能直接加指数的.。。1.5是用来伸缩的
        return Math.pow(Math.abs(x - centerX),2.0/3)/1.5 + Math.pow(Math.abs(y - centerY),2.0/3) <= Math.pow(radius,2.0/3);
    }
}
