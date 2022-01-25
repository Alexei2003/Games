package zmeika;

import javax.swing.*;
import java.awt.*;

public class Tutorial extends JPanel{
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String text5;
    private String text6;
    private String text7;
    private String text8;
    private Image berry1;
    private Image berry2;
    private Image berry3;
    private Image keybord;
    public Tutorial(){
        loadImage();
        loadText();
    }

    public Image loadImage() {
        ImageIcon iib1 = new ImageIcon("classes\\Berry1.png");
        berry1 = iib1.getImage();
        ImageIcon iib2 = new ImageIcon("classes\\Berry2.png");
        berry2 = iib2.getImage();
        ImageIcon iib3 = new ImageIcon("classes\\Berry3.png");
        berry3 = iib3.getImage();
        ImageIcon iik = new ImageIcon("classes\\Keybord.png");
        keybord = iik.getImage();
        return(berry1);
    }

    public String loadText(){
        text7 = "Задача. Набрать как можно больше очков за";
        text8 = "отведённое время";
        text1 = "При поедании, ягода увеличивает длину змейки на 1";
        text2 = "При поедании, ягода увеличивает длину змейки на 2";
        text3 = "При поедании, ягода увеличивает длину змейки на 3";
        text4 = "Управление осуществляется при ";
        text5 = "помощи клавиш стрелки, распо-";
        text6 = "ложенных на клавиатуре";
        return(text1);
    }

    @Override
    protected void paintComponent(Graphics e){
        super.paintComponents(e);
        e.setColor(Color.white);
        Font font = new Font("Serif", Font.ITALIC, 20);
        e.setFont(font);
        e.drawImage(berry1,10,75,this);
        e.drawImage(berry2,10,100,this);
        e.drawImage(berry3,10,125,this);
        e.drawImage(keybord,10,175,this);
        e.drawString(text7,10,25);
        e.drawString(text8,10,50);
        e.drawString(text1,35,91);
        e.drawString(text2,35,116);
        e.drawString(text3,35,141);
        e.drawString(text4,180,191);
        e.drawString(text5,180,216);
        e.drawString(text6,180,241);
    }
}
