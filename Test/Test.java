package project.Test;

import project.ColorModel.*;
import project.Content.*;
import project.FontScale.*;
import project.FrequencyCounts.FrequencyCounts;
import project.InBound.*;
import project.Tokenization.Tokenization;

import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //把所有配色方案都激活
        MyColorModel mc= new MyColorModel();
        mc.BW_MODEL();
        mc.GREEN_MODEL();
        mc.PURPLE_MODEL();
        mc.BLUE_MODEL();
        mc.RED_MODEL();
        mc.YELLOW_MODEL();

        System.out.println("Please enter the file name that you want to generate a word cloud:");
        System.out.print(">");
        String targetFile = input.nextLine();
        //System.out.println("The words and their frequency:");
        Tokenization tz = new Tokenization();
        ArrayList<String> contents = tz.tokenization(targetFile);
        FrequencyCounts fc = new FrequencyCounts();
        ArrayList<String> words = fc.count(contents);
        fontScale ps = new fontScale();
        ps.power(words);

        System.out.println("\nOur word cloud has two pattern-- Circle、Star");
        System.out.println("And five color model-- BW_MODEL、GREEN_MODEL、PURPLE_MODEL、BLUE_MODEL、RED_MODEL、YELLOW_MODEL");
        System.out.println("\nChoose a pattern you like (if you enter wrong name, it will be a circle):");
        System.out.print(">");
        String boundName = input.nextLine();
        //the radius can't be to small or too big
        int radius;
        boolean isProperRadius = false;

        System.out.println("Enter the radius of pattern (tip: 300-400 is suitable):");
        System.out.print(">");
        radius = input.nextInt();
        while (!isProperRadius) {
            if (radius < ps.getFontSize()[0] * words.get(0).length()) {
                System.out.println("The radius is too small");
                isProperRadius = false;
            }else if (radius > 400) {
                System.out.println("The radius is too big");
                isProperRadius = false;
            }else
                isProperRadius= true;
            if (!isProperRadius) {
                System.out.println("Enter the radius of pattern (tip: 300-400 is suitable):");
                System.out.print(">");
                radius = input.nextInt();
            }
        }


        System.out.println("Choose a color model you like(if you enter wrong name, it will be BW_MODEL):");
        String giveUp = input.nextLine();//因为nextLine放在nextInt后面会只读取换行
        System.out.print(">");
        String colorModelName = input.nextLine();

        Boundable bound;
        if (boundName.equals("Circle") || boundName.equals("circle") || boundName.equals("CIRCLE")) {
            bound = new Circle(radius);
        }else if (boundName.equals("Star") || boundName.equals("star") || boundName.equals("STAR")) {
             bound = new Star(radius);
        }
        else
            bound = new Circle(radius);

        System.out.println("Be patient please, it's so many words!");

        Color backGroundColor = mc.getBackgroundColorOfModel(colorModelName);
        TextBoard tb = new TextBoard(1300, 900, backGroundColor);

        for (int i = 0; i < words.size(); i++){
            ChineseWords cw = new ChineseWords(words.get(i));
            tb.write(cw, ps.getFontSize()[i], colorModelName, bound);
        }
        tb.display();
        tb.saveWordCloud();
        System.out.println("It's done!\nAnd has been saved as a jpg into-----" +
                "\nC:\\Users\\91426\\Downloads\\aaa\\Java\\project\\word-cloud");
    }
}
