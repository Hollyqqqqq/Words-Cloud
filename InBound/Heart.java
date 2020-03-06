package project.InBound;

public class Heart implements Boundable{
    private int radius;
    private int centerX;
    private int centerY;

    public Heart(int r){
            radius = r;
            //这里设置成定值因为我的窗口大小要固定成1200和900
            centerX = 600;
            centerY = 450;
        }

        @Override
        public void setRadius(int r){
            radius = r;
        }

        @Override
        public int getRadius(){
            return radius;
        }

        //use the function (x-a)^2 + ((y-b) - (x-a)^(2/3))^2 = r
        @Override
        public boolean CheckInBound(int x, int y){
            return (((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY) - radius)
                    *((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY) - radius)
                    *((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY) - radius)
                    - (x - centerX)*(x - centerX)*(y - centerY)*(y - centerY)*(y - centerY)) <= 0;
        }
}
