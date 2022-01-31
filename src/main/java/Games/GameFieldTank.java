package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

public class GameFieldTank extends JPanel implements ActionListener {
    private final int SIZE = 640;
    private final int DOT_SIZE = 32;
    private final int ALL_DOTS = 400;
    private int bots=4;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int[] [] Massive = new int[bots+1][7];
    private int[] [] [] Projectail = new int[bots+1][21][5];
    private int N;
    private int Temp1;
    private int Temp2;
    private int T2;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean fire = false;
    private Image barikada;
    private Image tank1;
    private Image tank2;
    private Image tank3;
    private Image tank4;
    private Image bomb;
    private Image tank21;
    private Image tank22;
    private Image tank23;
    private Image tank24;
    private Image bomb2;
    private Image[] Image = new Image[bots+1];
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
        Image[1] = iib2.getImage();
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
        Massive[3][2] = rand.nextInt(20)*32;
        Massive[3][3] = rand.nextInt(20)*32;
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
        for(int j=1; j<bots+1; j++){
            for(int i = 1; i<20; i++){
                Projectail[j][i][1] = 0;
                Projectail[j][i][2] = - 32;
                Projectail[j][i][3] = - 32;
                Projectail[j][i][4] = 0;
            }
        }
        for(int j=1; j<bots+1; j++) {
            Massive[j][6]=1;
        }
        Massive[1][4]=1;
    }

    private void timer() {
        timer = new Timer(500, this);
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
                if(Massive[i][4]==Temp1){
                    Massive[i][6]=1;
                }
                Massive[i][4]=Temp1;
            }else{
                if(Massive[i][4]==Temp2){
                    Massive[i][6]=1;
                }
                Massive[i][4]=Temp2;
            }
            if((Massive[1][2]-32 <= Massive[i][2]) && (Massive[1][2]+32 >= Massive[i][2]) || (Massive[1][3]-32 <= Massive[i][3]) && (Massive[1][3]+32 >= Massive[i][3])){
                Massive[i][5]=1;
            }
            T2=2;
        }else{
            T2=1;
            Massive[i][6]=1;
        }
    }

    private void Gamer() {
        if (up) {
            if (Massive[1][4] == 1) {
                Massive[1][6]=1;
            }
            Massive[1][4] = 1;
            up = false;
        } else {
            if (right) {
                if (Massive[1][4] == 2) {
                    Massive[1][6]=1;
                }
                Massive[1][4] = 2;
                right = false;
            } else {
                if (down) {
                    if (Massive[1][4] == 3) {
                        Massive[1][6]=1;
                    }
                    Massive[1][4] = 3;
                    down = false;
                } else {
                    if (left) {
                        if (Massive[1][4] == 4) {
                            Massive[1][6]=1;
                        }
                        Massive[1][4] = 4;
                        left = false;
                    }
                }
            }
        }
        if (fire) {
            Massive[1][5] = 1;
            fire = false;
        }
    }

    private void Tank(int i){
        if (i==1){
            HashMap<Integer,Image> map = new HashMap<>();
            map.put(1,tank1);
            map.put(2,tank2);
            map.put(3,tank3);
            map.put(4,tank4);
            Image[i]=map.get(Massive[1][4]);
        }else{
            HashMap<Integer,Image> map = new HashMap<>();
            map.put(1,tank21);
            map.put(2,tank22);
            map.put(3,tank23);
            map.put(4,tank24);
            Image[i]=map.get(Massive[i][4]);
        }
    }

    private void BOTS(int i){
        for(int j=1; j<70; j++){
            if ((Massive[i][2]+32 == x[j]) && (Massive[i][3] == y[j]) && (Massive[i][4]==2)){
                Massive[i][6]=0;
            }
            if ((Massive[i][2]-32 == x[j]) && (Massive[i][3] == y[j]) && (Massive[i][4]==4)){
                Massive[i][6]=0;
            }
            if ((Massive[i][3] +32 == y[j]) && (Massive[i][2] == x[j]) && (Massive[i][4]==3)){
                Massive[i][6]=0;
            }
            if ((Massive[i][3] -32 == y[j]) && (Massive[i][2] == x[j]) && (Massive[i][4]==1)){
                Massive[i][6]=0;
            }
        }

        if ((Massive[i][2]+32 == 640)&& (Massive[i][4]==2)){
            Massive[i][6]=0;
        }
        if ((Massive[i][2]-32 == 0) && (Massive[i][4]==4)){
            Massive[i][6]=0;
        }
        if ((Massive[i][3]+32 == 640) && (Massive[i][4]==3)){
            Massive[i][6]=0;
        }
        if ((Massive[i][3]-32 == 0) && (Massive[i][4]==1)){
            Massive[i][6]=0;
        }

        if(Massive[i][6]==1) {
            if (Massive[i][4] == 1){
                Massive[i][3] = Massive[i][3] - 32;
            } else {
                if (Massive[i][4] == 2){
                    Massive[i][2] = Massive[i][2] + 32;
                } else {
                    if (Massive[i][4] == 3){
                        Massive[i][3] = Massive[i][3] + 32;
                    } else {
                        if (Massive[i][4] == 4){
                            Massive[i][2] = Massive[i][2] - 32;
                        }
                    }
               }
           }
        }
        Massive[i][6]=0;

        if (Massive[i][5]==1){
            for(int j=1; j<20; j++){
                if (Projectail[i][j][1]==0){
                    Projectail[i][j][1]=1;
                    Projectail[i][j][2]=Massive[i][2];
                    Projectail[i][j][3]=Massive[i][3];
                    Projectail[i][j][4]=Massive[i][4];
                    j=21;
                }
            }
            Massive[i][5]=0;
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
        Gamer();
        Tank(1);
        Two Two = new Two();
        Two.start();
        Three Three = new Three();
        Three.start();
        Four Four = new Four();
        Four.start();
        if(Two.isAlive()){
            try{
                Two.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        if(Three.isAlive()){
            try{
                Three.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        if(Four.isAlive()){
            try{
                Four.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        for(int i=1; i<bots+1; i++){
            BOTS(i);
        }
        repaint();
    }

    class Two extends Thread{
        int i = 2;
        @Override
        public void run(){
            bot(i);
            Tank(i);
        }
    }

    class Three extends Thread{
        int i = 3;
        @Override
        public void run(){
            bot(i);
            Tank(i);
        }
    }

    class Four extends Thread{
        int i = 4;
        @Override
        public void run(){
            bot(i);
            Tank(i);
        }

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
        for (int i=2; i<bots+1; i++) {
            for (int j = 1; j < 20; j++) {
                if (Projectail[i][j][1] == 1) {
                    g.drawImage(bomb2, Projectail[i][j][2], Projectail[i][j][3], this);
                }
            }
        }

        g.drawImage(Image[1], Massive[1][2], Massive[1][3], this);
        for (int j=2; j<bots+1; j++){
            g.drawImage(Image[j], Massive[j][2], Massive[j][3], this);

        }
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
            if(key == KeyEvent.VK_SPACE){
                fire = true;
            }
        }
    }
}
