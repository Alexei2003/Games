package zmeika;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class GameFieldTest extends KeyAdapter {
    private boolean berry1;
    private boolean berry2;
    private boolean berry3;
    private boolean flag1;
    private Image t;
    GameField GF = new GameField();

    @Test
    public void initGame() {
        Assert.assertEquals(80,GF.initGame());
    }

    @Test
    public void berr1T(){
        Assert.assertTrue(GF.berry1(1,2,1,2));
    }

    @Test
    public void berr1F(){
        Assert.assertFalse(GF.berry1(0,1,1,2));
    }

    @Test
    public void createBerry1() {
        if ((GF.createBerry1() < 321) && (GF.createBerry1() > -1)){
            berry1 = true;
        }
        Assert.assertTrue(berry1);
    }

    @Test
    public void berr2T(){
        Assert.assertTrue(GF.berry2(1,2,1,2));
    }

    @Test
    public void berr2F(){
        Assert.assertFalse(GF.berry2(0,1,1,2));
    }

    @Test
    public void createBerry2() {
        if ((GF.createBerry2() < 321) && (GF.createBerry2() > -1)){
            berry2 = true;
        }
        Assert.assertTrue(berry2);
    }

    @Test
    public void berr3T(){
        Assert.assertTrue(GF.berry3(1,2,1,2));
    }

    @Test
    public void berr3F(){
        Assert.assertFalse(GF.berry3(0,1,1,2));
    }

    @Test
    public void createBerry3() {
        if ((GF.createBerry3() < 321) && (GF.createBerry3() > -1)){
            berry3 = true;
        }
        Assert.assertTrue(berry3);
    }

    @Test
    public void loadImages() {
        ImageIcon test = new ImageIcon("classes\\dot.png");
        t = test.getImage();
        if (GF.loadImages() == t){
            flag1 = true;
        }
        Assert.assertTrue(flag1);
    }

    @Test
    public void paintComponent() {

    }
    
    @Test
    public void timer1() {
        Assert.assertEquals(3,GF.Timer(1,3,1));
    }

    @Test
    public void timer2() {
        Assert.assertEquals(3,GF.Timer(5,3,10));
    }

    @Test
    public void timer3() {
        Assert.assertEquals(2,GF.Timer(5,3,0));
    }

    @Test
    public void timer4() {
        Assert.assertEquals(0,GF.Timer(0,0,0));
    }
    @Test
    public void move(){
        Assert.assertEquals(5, GF.move(5,4,1));
    }

    @Test
    public void left() {
        Assert.assertEquals(80,GF.horizontal(true,false));
    }

    @Test
    public void right() {
        Assert.assertEquals(112,GF.horizontal(false,true));
    }

    @Test
    public void up() {
        Assert.assertEquals(32,GF.vertical(true,false));
    }

    @Test
    public void down() {
        Assert.assertEquals(64,GF.vertical(false,true));
    }

    @Test
    public void checkBerry1T() {
        Assert.assertTrue(GF.checkBerry1(1,1,1,1));
    }
    @Test
    public void checkBerry1F() {
        Assert.assertFalse(GF.checkBerry1(0,1,1,1));
    }

    @Test
    public void checkBerry2T() {
        Assert.assertTrue(GF.checkBerry2(1,1,1,1));
    }
    @Test
    public void checkBerry2F() {
        Assert.assertFalse(GF.checkBerry2(1,1,1,0));
    }
    @Test
    public void checkBerry3T() {
        Assert.assertTrue(GF.checkBerry3(1,1,1,1));
    }
    
    @Test
    public void checkBerry3F() {
        Assert.assertFalse(GF.checkBerry3(0,1,1,0));
    }
}
