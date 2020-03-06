package project.ColliCheck;

import java.awt.*;

public interface Colliable {
    boolean collide(Colliable colliable);
    Point getPosition();
    Dimension getDimension();
    double getAngle();
}
/*I create this interface because in the beginning I think there may be just several words.
So I want to add a padder such as "*" "+" or "-" to pad the world cloud uniformly.
padder and ChineseWords are both Collidable.
But the result turns out to be enough words.
So I needn't design the padder.
But I just keep the interface, lazy to delete and modify my code. */
