package project.InBound;


//以整个画面的几何中心作为圆心
public class Circle implements Boundable{
    private int radius;
    private int centerX;
    private int centerY;

    public Circle(int r){
        radius = r;
        //这里设置成定值因为我的窗口大小要固定成1200和900
        centerX = 650;
        centerY = 420;
    }

    @Override
    public void setRadius(int r){
        radius = r;
    }

    @Override
    public int getRadius(){
        return radius;
    }

    //use the function (x-a)^2 + (y-b)^2 = r^2
    @Override
    public boolean CheckInBound(int x, int y){
        return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= radius * radius;
    }
}
