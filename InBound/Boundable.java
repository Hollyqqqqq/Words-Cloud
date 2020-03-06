package project.InBound;

public interface Boundable {
    boolean CheckInBound(int x, int y);
    void setRadius(int r);//如果后面没用到我就要把它删了
    int getRadius();
}
