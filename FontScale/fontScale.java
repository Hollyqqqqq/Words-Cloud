package project.FontScale;

import java.util.ArrayList;

//字体大小跟频率的关系
public class fontScale {
    private int[] fontSize;

    public fontScale(){
    }

    //The font size has been divided into three parts.
    public void power(ArrayList arr) {
        //用一个数组储存对应的字体大小
        fontSize = new int[arr.size()];
        // The first five words lie in 90 to 130.
        double decrease = (130 - 90)/5.0;
        for (int i = 0; i < 5; i++) {
            fontSize[i] = (int) (130 - decrease*i);
        }
        // One third of the left words lie in 20 to 60
        double decrease1 = (60 - 20)/((arr.size()-5)*(1.0/3));
        for (int i = 5; i < (arr.size()-5)*(1.0/3); i++){
            fontSize[i] = (int)(60 - decrease1*i);
        }
        //all the words left is 10
        for (int i = (int)((arr.size()-5)*(1.0/3)); i < arr.size(); i++){
            fontSize[i] = 10;
        }
    }

    public int[] getFontSize(){
        return fontSize;
    }
}
