package zmeika;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class TutorialTest {
    private boolean flag1;
    private boolean flag2;
    private Image t;
    Tutorial T = new Tutorial();

    @Test
    public void loadImage() {
        ImageIcon test = new ImageIcon("classes\\Berry1.png");
        t = test.getImage();
        if (T.loadImage() == t){
            flag1 = true;
        }
        Assert.assertTrue(flag1);
    }

    @Test
    public void loadText() {
        if (T.loadText() == "При поедании, ягода увеличивает длину змейки на 1"){
            flag2 = true;
        }
        Assert.assertTrue(flag2);
    }

    @Test
    public void paintComponent() {
    }
}