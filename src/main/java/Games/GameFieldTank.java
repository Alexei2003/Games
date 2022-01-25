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

    private Timer timer;


    public GameFieldTank(){
        setBackground(Color.black);
        loadImages();
        initGame();

    }

    private void loadImages() {

    }

    private void initGame() {
        x1 = new Random().nextInt(20)*32;
        y1 = new Random().nextInt(20)*32;
        x2 = new Random().nextInt(20)*32;
        y2 = new Random().nextInt(20)*32;
        for (int i = 1; i < 81; i++){
            x[i] = new Random().nextInt(20)*32;
            y[i] = new Random().nextInt(20)*32;
            for (int j = 1; j < i; j++){
                if (((x[i] <= x[j]+1) && (x[i] >= x[j]-1) && (y[i] <= y[j]+1) && (y[i] >= y[j]-1)) || (x[i] == x1) || (x[i] == x2) || (y[i] == y1) || (y[i] == y2)){
                    i = i-1;
                    j = 120;
                }else{
                    System.out.println(x[i]);
                }
            }
        }
    }

    private void timer() {
        timer = new Timer(250, this);
        timer.start();
    }

    // Отрисовка объектов
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }
}
