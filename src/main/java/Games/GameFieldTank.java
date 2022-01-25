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
    private int[] [] Projectail = new int[21][5];
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean leftF = false;
    private boolean rightF = false;
    private boolean upF = false;
    private boolean downF = false;
    private boolean fire = false;
    private Image barikada;
    private Image tank1;
    private Image tank2;
    private Image tank3;
    private Image tank4;
    private Image tank;
    private Image bomb;
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
        ImageIcon iib6 = new ImageIcon("resources\\tank\\fire.png");
        bomb = iib6.getImage();
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
        for(int i = 1; i<20; i++){
            Projectail[i][1] = 0;
            Projectail[i][2] = - 32;
            Projectail[i][3] = - 32;
            Projectail[i][4] = 0;
        }
    }

    private void timer() {
        timer = new Timer(250, this);
        timer.start();
    }

    private void Rotation(){
        if (upF){
            tank = tank1;
        }else{
            if (rightF){
                tank = tank2;
            }else{
                if (downF){
                    tank = tank3;
                }else{
                    if (leftF){
                        tank = tank4;
                    }
                }
            }
        }
    }

    private void move(){
        if (up){
            y1 = y1-32;
            up = false;
        }else{
            if (right){
                x1 = x1+32;
                right = false;
            }else{
                if (down){
                    y1 = y1+32;
                    down  = false;
                }else{
                    if (left){
                        x1 = x1-32;
                        left = false;
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
        if ((x1+32 == 640)&& (right)){
            right = false;
        }
        if ((x1-32 == 0) && (left)){
            left = false;
        }
        if ((y1+32 == 640) && (down)){
            down = false;
        }
        if ((y1-32 == 0) && (up)){
            up = false;
        }

    }

    private void Projectail(){
        if (fire){
            for(int i=1; i<20; i++){
                if (Projectail[i][1]==0){
                    if (upF){
                        Projectail[i][1]=1;
                        Projectail[i][2]=x1;
                        Projectail[i][3]=y1;
                        Projectail[i][4]=1;
                        i=21;
                    }else{
                        if (rightF){
                            Projectail[i][1]=1;
                            Projectail[i][2]=x1;
                            Projectail[i][3]=y1;
                            Projectail[i][4]=2;
                            i=21;
                        }else{
                            if (downF){
                                Projectail[i][1]=1;
                                Projectail[i][2]=x1;
                                Projectail[i][3]=y1;
                                Projectail[i][4]=3;
                                i=21;
                            }else{
                                if (leftF){
                                    Projectail[i][1]=1;
                                    Projectail[i][2]=x1;
                                    Projectail[i][3]=y1;
                                    Projectail[i][4]=4;
                                    i=21;
                                }
                            }
                        }
                    }
                }
            }
            fire=false;
        }
    }

    private void MoveProjectail(){
        for(int i=1; i<21; i++){
            if(Projectail[i][4]== 1){
                Projectail[i][3]=Projectail[i][3]-32;
                if(Projectail[i][3]==-32){
                    Projectail[i][1]=0;
                }
                for(int j=1; j<70; j++){
                    if((x[j]==Projectail[i][2]) && (y[j]==Projectail[i][3])){
                        Projectail[i][1]=0;
                    }
                }
            }else{
                if(Projectail[i][4]== 2){
                    Projectail[i][2]=Projectail[i][2]+32;
                    if(Projectail[i][2]==672){
                        Projectail[i][1]=0;
                    }
                    for(int j=1; j<70; j++){
                        if((x[j]==Projectail[i][2]) && (y[j]==Projectail[i][3])){
                            Projectail[i][1]=0;
                        }
                    }
                }else{
                    if(Projectail[i][4]== 3){
                        Projectail[i][3]=Projectail[i][3]+32;
                        if(Projectail[i][3]==672){
                            Projectail[i][1]=0;
                        }
                        for(int j=1; j<70; j++){
                            if((x[j]==Projectail[i][2]) && (y[j]==Projectail[i][3])){
                                Projectail[i][1]=0;
                            }
                        }
                    }else{
                        if(Projectail[i][4]== 4){
                            Projectail[i][2]=Projectail[i][2]-32;
                            if(Projectail[i][2]==-32){
                                Projectail[i][1]=0;
                            }
                            for(int j=1; j<70; j++){
                                if((x[j]==Projectail[i][2]) && (y[j]==Projectail[i][3])){
                                    Projectail[i][1]=0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        CheckColision();
        Rotation();
        move();
        Projectail();
        MoveProjectail();
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
        for(int i=1; i<20; i++){
            if(Projectail[i][1] == 1){
                g.drawImage(bomb,Projectail[i][2],Projectail[i][3],this);
            }
        }
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                if(leftF){
                    left = true;
                }
                leftF = true;
                rightF = false;
                upF = false;
                downF = false;
            }
            if(key == KeyEvent.VK_RIGHT){
                if(rightF){
                    right = true;
                }
                rightF = true;
                leftF = false;
                upF = false;
                downF = false;
            }

            if(key == KeyEvent.VK_UP){
                if(upF){
                    up = true;
                }
                upF = true;
                leftF = false;
                rightF = false;
                downF = false;
            }
            if(key == KeyEvent.VK_DOWN){
                if(downF){
                    down = true;
                }
                downF = true;
                leftF = false;
                rightF = false;
                upF = false;
            }
            if(key == KeyEvent.VK_SPACE){
                fire = true;
            }
        }
    }
}
