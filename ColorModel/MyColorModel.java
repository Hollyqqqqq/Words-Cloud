package project.ColorModel;

import java.util.ArrayList;
import java.awt.*;

public class MyColorModel{
    //创建两个ArrayList，color和它相对应的name同时添加，
    // 保证他们相应的下标一样，这样就可以根据名字找到相对应的颜色
    //如果不是static，再次使用这个方法时这些数组都会被清空
    private static ArrayList<Color[]> colorModel = new ArrayList<>();
    private static ArrayList<String> ModelName = new ArrayList<>();
    private int colorModelIndex;
    private static final int DEFAULT_INDEX = 0;

    public MyColorModel(){
    }

    //one model has several colors，这样写我必须要用一下这个方法，ArrayList里才会有这个model
    public void BW_MODEL(){
        Color[] BW_MODEL = new Color[6];
        BW_MODEL[0] = new Color(243,255,249);
        BW_MODEL[1] = new Color(243,255,249);
        BW_MODEL[2] = new Color(243,255,249);
        BW_MODEL[3] = new Color(243,255,249);
        BW_MODEL[4] = new Color(243,255,249);
        BW_MODEL[5] = new Color(0,173,233);//背景颜色
        colorModel.add(BW_MODEL);
        ModelName.add("BW_MODEL");
    }

    public void GREEN_MODEL(){
        Color[] GREEN_MODEL = new Color[6];
        GREEN_MODEL[0] = new Color(161,215,180);
        GREEN_MODEL[1] = new Color(146,206,182);
        GREEN_MODEL[2] = new Color(130,198,184);
        GREEN_MODEL[3] = new Color(115,224,190);
        GREEN_MODEL[4] = new Color(100,181,191);
        GREEN_MODEL[5] = new Color(255,251,240);//背景颜色
        colorModel.add(GREEN_MODEL);
        ModelName.add("GREEN_MODEL");
    }

    public void PURPLE_MODEL(){
        Color[] PURPLE_MODEL = new Color[6];
        PURPLE_MODEL[0] = new Color(121,104,232);
        PURPLE_MODEL[1] = new Color(184,174,246);
        PURPLE_MODEL[2] = new Color(149,135,236);
        PURPLE_MODEL[3] = new Color(172,167,202);
        PURPLE_MODEL[4] = new Color(217,211,255);
        PURPLE_MODEL[5] = new Color(0,0,0);//背景颜色
        colorModel.add(PURPLE_MODEL);
        ModelName.add("PURPLE_MODEL");
    }

    public void BLUE_MODEL(){
        Color[] BLUE_MODEL = new Color[6];
        BLUE_MODEL[0] = new Color(5,65,99);
        BLUE_MODEL[1] = new Color(23,131,151);
        BLUE_MODEL[2] = new Color(16,96,124);
        BLUE_MODEL[3] = new Color(34,177,211);
        BLUE_MODEL[4] = new Color(44,118,181);
        BLUE_MODEL[5] = new Color(248,255,250);//背景颜色
        colorModel.add(BLUE_MODEL);
        ModelName.add("BLUE_MODEL");
    }

    public void RED_MODEL(){
        Color[] RED_MODEL = new Color[6];
        RED_MODEL[0] = new Color(172,52,51);
        RED_MODEL[1] = new Color(223,159,132);
        RED_MODEL[2] = new Color(172,52,51);
        RED_MODEL[3] = new Color(231,167,139);
        RED_MODEL[4] = new Color(239,219,184);
        RED_MODEL[5] = new Color(52,26,27);//背景颜色
        colorModel.add(RED_MODEL);
        ModelName.add("RED_MODEL");
    }

    public void YELLOW_MODEL(){
        Color[] YELLOW_MODEL = new Color[6];
        YELLOW_MODEL[0] = new Color(241,232,132);
        YELLOW_MODEL[1] = new Color(159,114,30);
        YELLOW_MODEL[2] = new Color(253,251,192);
        YELLOW_MODEL[3] = new Color(253,229,50);
        YELLOW_MODEL[4] = new Color(219,200,82);
        YELLOW_MODEL[5] = new Color(0,0,0);//背景颜色
        colorModel.add(YELLOW_MODEL);
        ModelName.add("YELLOW_MODEL");
    }

    //获得一个配色方案的子颜色
    public Color getOneColorOfModel(String name){
        for (int i = 0; i < ModelName.size(); i++){
            if (ModelName.get(i).equals(name)) {
                colorModelIndex = i;
                break;
            }
            else if (i == ModelName.size() - 1)//如果没有相对应的名字，就用default值
                colorModelIndex = DEFAULT_INDEX;

        }
        int n = (int)(Math.random()*5);
        return colorModel.get(colorModelIndex)[n];
    }

    //获得一个配色方案的背景色
    public Color getBackgroundColorOfModel(String name){
        for (int i = 0; i < ModelName.size(); i++){
            if (ModelName.get(i).equals(name)) {
                colorModelIndex = i;
                break;
            }
            else if (i == ModelName.size() - 1)//如果没有相对应的名字，就用default值
                colorModelIndex = DEFAULT_INDEX;
        }
        return colorModel.get(colorModelIndex)[5];
    }
}
