package project.ColliCheck;


public class ColliChecker {
    //在Point里面，position指的是左下角的点的坐标

    //如果没有这个，在别的class里面就不能用这个方法
    public ColliChecker(){
    }

    public boolean collide(Colliable colli1, Colliable colli2) {

        //两个词语都是水平的情况下检查判断
        /*
             __________________
            |                 |
            |                 |
            |   ______________|____
            |  |              |   |
            |__|______________|   |
         x1,y1 |                  |
               |__________________|
          x2,y2
          */
        if (colli1.getAngle() == 0 && colli2.getAngle() == 0) {
            if ((colli1.getPosition().x + colli1.getDimension().width < colli2.getPosition().x)
                    || (colli2.getPosition().x + colli2.getDimension().width < colli1.getPosition().x)) {
                return false;
            } else if ((colli1.getPosition().y - colli1.getDimension().height > colli2.getPosition().y)
                    || (colli2.getPosition().y - colli2.getDimension().height > colli1.getPosition().y)) {
                return false;
            } else
                return true;
        }

        //一个水平一个竖直的情况下检查碰撞,有两种情况，判断方法复杂
        /*
             ________________________
            |                       |
            |                       |
            |                       |
            |  x2, y2__________     |
            |__|______________|_____|
         x1,y1 |              |
               |              |
               |              |
               |              |
               |              |
               |______________|

          */
        else if ((colli1.getAngle() == 0 && colli2.getAngle() == 90)) {
            if ((colli1.getPosition().x + colli1.getDimension().width < colli2.getPosition().x)
                    || (colli2.getPosition().x + colli2.getDimension().width < colli1.getPosition().x)) {
                return false;
            } else if (colli1.getPosition().y < colli2.getPosition().y){
                return false;
            } else if (colli1.getPosition().y > colli2.getPosition().y &&
                    (colli1.getPosition().y - colli1.getDimension().height > colli2.getPosition().y + colli2.getDimension().height)) {
                return false;
            } else
                return true;
        }
        else if ((colli1.getAngle() == 90 && colli2.getAngle() == 0)){
            if ((colli1.getPosition().x + colli1.getDimension().width < colli2.getPosition().x)
                    || (colli2.getPosition().x + colli2.getDimension().width < colli1.getPosition().x)) {
                return false;
            } else if (colli1.getPosition().y > colli2.getPosition().y){
                return false;
            } else if (colli1.getPosition().y < colli2.getPosition().y &&
                    (colli1.getPosition().y + colli1.getDimension().height < colli2.getPosition().y - colli2.getDimension().height)) {
                return false;
            } else
                return true;
        }

        //两个都是竖直的情况下检查碰撞
        /*
             x1, y2_________
            |              |
            |              |
            |              |
            |              |
            |              |
            |  x2, y2______|___
            |__|___________|  |
               |              |
               |              |
               |              |
               |              |
               |              |
               |______________|

          */
        else if (colli1.getAngle() == 90 && colli2.getAngle() == 90) {
            if ((colli1.getPosition().x + colli1.getDimension().width < colli2.getPosition().x)
                    || (colli2.getPosition().x + colli2.getDimension().width < colli1.getPosition().x)) {
                return false;
            } else if ((colli1.getPosition().y + colli1.getDimension().height < colli2.getPosition().y)
                    || (colli2.getPosition().y + colli2.getDimension().height < colli1.getPosition().y)) {
                return false;
            } else
                return true;
        }
        else
            return true;
    }
}
