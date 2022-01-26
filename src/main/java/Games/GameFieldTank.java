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
    private int[] [] Massive = new int[3][6];
    private int[] [] [] Projectail = new int[3][21][5];
    private int N;
    private int T2;
    private int Temp1;
    private int Temp2;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean leftF = false;
    private boolean rightF = false;
    private boolean upF = false;
    private boolean downF = false;
    private boolean fire = false;
    private boolean left2 = false;
    private boolean right2 = false;
    private boolean up2 = false;
    private boolean down2 = false;
    private Image barikada;
    private Image tank1;
    private Image tank2;
    private Image tank3;
    private Image tank4;
    private Image tank5;
    private Image bomb;
    private Image tank21;
    private Image tank22;
    private Image tank23;
    private Image tank24;
    private Image tank25;
    private Image bomb2;
    private Timer timer;
    private Random rand =new Random();



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
        tank5 = iib2.getImage();
        tank1 = iib2.getImage();
        ImageIcon iib3 = new ImageIcon("resources\\tank\\tank2.png");
        tank2 = iib3.getImage();
        ImageIcon iib4 = new ImageIcon("resources\\tank\\tank3.png");
        tank3 = iib4.getImage();
        ImageIcon iib5 = new ImageIcon("resources\\tank\\tank4.png");
        tank4 = iib5.getImage();
        ImageIcon iib6 = new ImageIcon("resources\\tank\\fire.png");
        bomb = iib6.getImage();
        ImageIcon iib7 = new ImageIcon("resources\\tank\\tank21.png");
        tank21 = iib7.getImage();
        ImageIcon iib8 = new ImageIcon("resources\\tank\\tank22.png");
        tank22 = iib8.getImage();
        ImageIcon iib9 = new ImageIcon("resources\\tank\\tank23.png");
        tank23 = iib9.getImage();
        ImageIcon iib10 = new ImageIcon("resources\\tank\\tank24.png");
        tank24 = iib10.getImage();
        ImageIcon iib11 = new ImageIcon("resources\\tank\\fire2.png");
        bomb2 = iib11.getImage();
    }

    private void initGame() {
        Massive[1][2] = rand.nextInt(20)*32;
        Massive[1][3] = rand.nextInt(20)*32;
        Massive[2][2] = rand.nextInt(20)*32;
        Massive[2][3] = rand.nextInt(20)*32;
        for (int i = 1; i < 70; i++){
            x[i] = rand.nextInt(20)*32;
            y[i] = rand.nextInt(20)*32;
            for (int j = 1; j < i; j++){
                if (((x[i] <= x[j]+32) && (x[i] >= x[j]-32) && (y[i] <= y[j]+32) && (y[i] >= y[j]-32)) || (x[i] == Massive[1][2]) || (y[i] == Massive[1][3])){
                    i = i-1;
                    j = 120;
                }
            }
        }
        for(int j=1; j<3; j++){
            for(int i = 1; i<20; i++){
                Projectail[j][i][1] = 0;
                Projectail[j][i][2] = - 32;
                Projectail[j][i][3] = - 32;
                Projectail[j][i][4] = 0;
            }
        }
        T2=1;
    }

    private void timer() {
        timer = new Timer(250, this);
        timer.start();
    }

    private  void bot(int i){
        if(T2==1){
            N=rand.nextInt(2);
            if((Massive[1][2]-Massive[i][2]>0)){
                Temp1=2;
            }else{
                Temp1=4;
            }
            if((Massive[1][3]-Massive[i][3]>0)){
                Temp2=3;
            }else{
                Temp2=1;
            }
            if(N==1){
                Massive[i][4]=Temp1;
            }else{
                Massive[i][4]=Temp2;
            }
            T2=2;
        }else{
            T2=1;
        }
    }

    private void user2(int i){
        if((Massive[1][2]-32 <= Massive[i][2]) && (Massive[1][2]+32 >= Massive[i][2]) || (Massive[1][3]-32 <= Massive[i][3]) && (Massive[1][3]+32 >= Massive[i][3])){
            Massive[i][5]=1;
        }
    }

    private void Gamer(){
        if(T2==1){
            if(up){
                Massive[1][4]=1;
                up=false;
            }else{
                if(right){
                    Massive[1][4]=2;
                    right=false;
                }else{
                    if(down){
                        Massive[1][4]=3;
                        down=false;
                    }else{
                        if(left){
                            Massive[1][4]=4;
                            left=false;
                        }
                    }
                }
            }
            T2=2;
        }else{
            T2=1;
        }
    }

    private void Tank(int i){
        if (i==1){
            if ((Massive[1][4]==1) && (T2==2)){
                tank5 = tank1;
            }else{
                if ((Massive[1][4]==2) && (T2==2)){
                    tank5 = tank2;
                }else{
                    if ((Massive[1][4]==3) && (T2==2)){
                        tank5 = tank3;
                    }else{
                        if ((Massive[1][4]==4) && (T2==2)){
                            tank5 = tank4;
                        }
                    }
                }
            }
        }else{
            if(i==2){
                if ((Massive[2][4]==1) && (T2==2)){
                    tank25 = tank21;
                }else{
                    if ((Massive[2][4]==2) && (T2==2)){
                        tank25 = tank22;
                    }else{
                        if ((Massive[2][4]==3) && (T2==2)){
                            tank25 = tank23;
                        }else{
                            if ((Massive[2][4]==4) && (T2==2)){
                                tank25 = tank24;
                            }
                        }
                    }
                }
            }
        }
    }

    private void BOTS(int i){
        for(int j=1; j<70; j++){
            if ((Massive[i][2]+32 == x[j]) && (Massive[i][3] == y[j]) && (Massive[i][4]==2)){
                Massive[i][4]=0;
            }
            if ((Massive[i][2]-32 == x[j]) && (Massive[i][3] == y[j]) && (Massive[i][4]==4)){
                Massive[i][4]=0;
            }
            if ((Massive[i][3] +32 == y[j]) && (Massive[i][2] == x[j]) && (Massive[i][4]==3)){
                Massive[i][4]=0;
            }
            if ((Massive[i][3] -32 == y[j]) && (Massive[i][2] == x[j]) && (Massive[i][4]==1)){
                Massive[i][4]=0;
            }
        }

        if ((Massive[i][2]+32 == 640)&& (Massive[i][4]==2)){
            Massive[i][4]=0;
        }
        if ((Massive[i][2]-32 == 0) && (Massive[i][4]==4)){
            Massive[i][4]=0;
        }
        if ((Massive[i][3]+32 == 640) && (Massive[i][4]==3)){
            Massive[i][4]=0;
        }
        if ((Massive[i][3]-32 == 0) && (Massive[i][4]==1)){
            Massive[i][4]=0;
        }

        if ((Massive[i][4]==1) && (T2==1)){
            Massive[i][3] = Massive[i][3] -32;
        }else{
            if ((Massive[i][4]==2) && (T2==1)){
                Massive[i][2] = Massive[i][2]+32;
            }else{
                if ((Massive[i][4]==3) && (T2==1)){
                    Massive[i][3] = Massive[i][3] +32;
                }else{
                    if ((Massive[i][4]==4) && (T2==1)){
                        Massive[i][2] = Massive[i][2]-32;
                    }
                }
            }
        }

        if ((Massive[i][5]==1) && (T2==1)){
            for(int j=1; j<20; j++){
                if (Projectail[i][j][1]==0){
                    if (Massive[i][4]==1){
                        Projectail[i][j][1]=1;
                        Projectail[i][j][2]=Massive[i][2];
                        Projectail[i][j][3]=Massive[i][3];
                        Projectail[i][j][4]=1;
                        j=21;
                    }else{
                        if (Massive[i][4]==2){
                            Projectail[i][j][1]=1;
                            Projectail[i][j][2]=Massive[i][2];
                            Projectail[i][j][3]=Massive[i][3];
                            Projectail[i][j][4]=2;
                            j=21;
                        }else{
                            if (Massive[i][4]==3){
                                Projectail[i][j][1]=1;
                                Projectail[i][j][2]=Massive[i][2];
                                Projectail[i][j][3]=Massive[i][3];
                                Projectail[i][j][4]=3;
                                j=21;
                            }else{
                                if (Massive[i][4]==4){
                                    Projectail[i][j][1]=1;
                                    Projectail[i][j][2]=Massive[i][2];
                                    Projectail[i][j][3]=Massive[i][3];
                                    Projectail[i][j][4]=4;
                                    j=21;
                                }
                            }
                        }
                    }
                }
            }
            Massive[i][5]=0;
        }

        if(T2==1){
            Massive[i][4]=0;
        }
        for(int j=1; j<21; j++){
            if(Projectail[i][j][4] == 1){
                Projectail[i][j][3]=Projectail[i][j][3]-32;
                if(Projectail[i][j][3]==-32){
                    Projectail[i][j][1]=0;
                }
                for(int k=1; k<70; k++){
                    if((x[k]==Projectail[i][j][2]) && (y[k]==Projectail[i][j][3])){
                        Projectail[i][j][1]=0;
                    }
                }
            }else{
                if(Projectail[i][j][4]== 2){
                    Projectail[i][j][2]=Projectail[i][j][2]+32;
                    if(Projectail[i][j][2]==672){
                        Projectail[i][j][1]=0;
                    }
                    for(int k=1; k<70; k++){
                        if((x[k]==Projectail[i][j][2]) && (y[k]==Projectail[i][j][3])){
                            Projectail[i][j][1]=0;
                        }
                    }
                }else{
                    if(Projectail[i][j][4]== 3){
                        Projectail[i][j][3]=Projectail[i][j][3]+32;
                        if(Projectail[i][j][3]==672){
                            Projectail[i][j][1]=0;
                        }
                        for(int k=1; k<70; k++){
                            if((x[k]==Projectail[i][j][2]) && (y[k]==Projectail[i][j][3])){
                                Projectail[i][j][1]=0;
                            }
                        }
                    }else{
                        if(Projectail[i][j][4]== 4){
                            Projectail[i][j][2]=Projectail[i][j][2]-32;
                            if(Projectail[i][j][2]==-32){
                                Projectail[i][j][1]=0;
                            }
                            for(int k=1; k<70; k++){
                                if((x[k]==Projectail[i][j][2]) && (y[k]==Projectail[i][j][3])){
                                    Projectail[i][j][1]=0;
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
        for(int i=1; i<3; i++){
            if(i==1){
                for(int j=1; j<3; j++) {
                    Gamer();
                    Tank(i);
                }
            }else{
                bot(i);
                user2(i);
                Tank(i);
            }
            BOTS(i);
        }
        repaint();
    }

    // Отрисовка объектов
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=1; i < 70; i++){
            g.drawImage(barikada, x[i], y[i], this);
        }
        for(int j=1; j<20; j++){
            if(Projectail[1][j][1] == 1){
                g.drawImage(bomb,Projectail[1][j][2],Projectail[1][j][3],this);
            }
        }
        for(int j=1; j<20; j++){
            if(Projectail[2][j][1] == 1){
                g.drawImage(bomb2,Projectail[2][j][2],Projectail[2][j][3],this);
            }
        }
        g.drawImage(tank5, Massive[1][2], Massive[1][3], this);
        g.drawImage(tank25, Massive[2][2], Massive[2][3], this);
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                left = true;
                leftF = true;
                rightF = false;
                upF = false;
                downF = false;
            }
            if(key == KeyEvent.VK_RIGHT){
                right = true;
                rightF = true;
                leftF = false;
                upF = false;
                downF = false;
            }

            if(key == KeyEvent.VK_UP){
                up = true;
                upF = true;
                leftF = false;
                rightF = false;
                downF = false;
            }
            if(key == KeyEvent.VK_DOWN){
                down = true;
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
