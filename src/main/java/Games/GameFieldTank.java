package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameFieldTank extends JPanel implements ActionListener {
    private final int SIZE = 640;
    private final int DOT_SIZE = 32;
    private final int ALL_DOTS = 400;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int[] [] Projectail = new int[21][4];
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame1 = true;
    private boolean inGame2 = true;
    private Image barikada;
    private Image tank1;
    private Image tank2;
    private Image tank3;
    private Image tank4;
    private Image tank;
    private Timer timer;



    public GameFieldTank(){
        setBackground(Color.black);
        loadImages();
        initGame();
        timer();
        addKeyListener(new GameFieldTank.FieldKeyListener());
        setFocusable(true);
    }

    private void loadImages() {
        ImageIcon iib1 = new ImageIcon("resources\\tank\\barikada.png");
        barikada = iib1.getImage();
        ImageIcon iib2 = new ImageIcon("resources\\tank\\tank1.png");
        tank = iib2.getImage();
        tank1 = iib2.getImage();
        ImageIcon iib3 = new ImageIcon("resources\\tank\\tank2.png");
        tank2 = iib3.getImage();
        ImageIcon iib4 = new ImageIcon("resources\\tank\\tank3.png");
        tank3 = iib4.getImage();
        ImageIcon iib5 = new ImageIcon("resources\\tank\\tank4.png");
        tank4 = iib5.getImage();
    }

    private Random rand =new Random();

    private void initGame() {
        x1 = rand.nextInt(20)*32;
        y1 = rand.nextInt(20)*32;
        x2 = rand.nextInt(20)*32;
        y2 = rand.nextInt(20)*32;
        for (int i = 1; i < 70; i++){
            x[i] = rand.nextInt(20)*32;
            y[i] = rand.nextInt(20)*32;
            for (int j = 1; j < i; j++){
                if (((x[i] <= x[j]+32) && (x[i] >= x[j]-32) && (y[i] <= y[j]+32) && (y[i] >= y[j]-32)) || (x[i] == x1) || (x[i] == x2) || (y[i] == y1) || (y[i] == y2)){
                    i = i-1;
                    j = 120;
                }
            }
        }
    }

    private void timer() {
        timer = new Timer(250, this);
        timer.start();
    }

    private void move(){
        if (up){
            y1 = y1-32;
            up = false;
            tank = tank1;
        }else{
            if (right){
                x1 = x1+32;
                right = false;
                tank = tank2;
            }else{
                if (down){
                    y1 = y1+32;
                    down  = false;
                    tank = tank3;
                }else{
                    if (left){
                        x1 = x1-32;
                        left = false;
                        tank = tank4;
                    }
                }
            }
        }
    }

    private void CheckColision(){
        for(int i=1; i<70; i++){
            if ((x1+32 == x[i]) && (y1 == y[i]) && (right)){
                right = false;
            }
            if ((x1-32 == x[i]) && (y1 == y[i]) && (left)){
                left = false;
            }
            if ((y1+32 == y[i]) && (x1 == x[i]) && (down)){
                down = false;
            }
            if ((y1-32 == y[i]) && (x1 == x[i]) && (up)){
                up = false;
            }
        }
    }

    private void Projectail(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       CheckColision();
       move();
       repaint();
    }

    // Отрисовка объектов
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=1; i < 70; i++){
            g.drawImage(barikada, x[i], y[i], this);
        }
        g.drawImage(tank, x1, y1, this);
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                left = true;
            }
            if(key == KeyEvent.VK_RIGHT){
                right = true;
            }

            if(key == KeyEvent.VK_UP){
                up = true;
            }
            if(key == KeyEvent.VK_DOWN){
                down = true;
            }
        }
    }
}
