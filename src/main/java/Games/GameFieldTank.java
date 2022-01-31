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
    private int T;
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
    private Image exposion;
    private Image[] Image = new Image[bots+1];
    private Timer timer;
    private Random rand =new Random();
    private String str;

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
        ImageIcon iib12 = new ImageIcon("resources\\tank\\exposion.png");
        exposion = iib12.getImage();
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
            Massive[j][1]=1;
            Massive[j][4]=1;
            Massive[j][6]=1;
        }
        T=1;
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

    private void Сollision(int i) {
        for (int j = 1; j < 70; j++) {
            if ((Massive[i][2] + 32 == x[j]) && (Massive[i][3] == y[j]) && (Massive[i][4] == 2)) {
                Massive[i][6] = 0;
            }
            if ((Massive[i][2] - 32 == x[j]) && (Massive[i][3] == y[j]) && (Massive[i][4] == 4)) {
                Massive[i][6] = 0;
            }
            if ((Massive[i][3] + 32 == y[j]) && (Massive[i][2] == x[j]) && (Massive[i][4] == 3)) {
                Massive[i][6] = 0;
            }
            if ((Massive[i][3] - 32 == y[j]) && (Massive[i][2] == x[j]) && (Massive[i][4] == 1)) {
                Massive[i][6] = 0;
            }
        }

        if ((Massive[i][2] + 32 == 640) && (Massive[i][4] == 2)) {
            Massive[i][6] = 0;
        }
        if ((Massive[i][2] - 32 == 0) && (Massive[i][4] == 4)) {
            Massive[i][6] = 0;
        }
        if ((Massive[i][3] + 32 == 640) && (Massive[i][4] == 3)) {
            Massive[i][6] = 0;
        }
        if ((Massive[i][3] - 32 == 0) && (Massive[i][4] == 1)) {
            Massive[i][6] = 0;
        }
    }

    private void move(int i) {
        if (Massive[i][6] == 1) {
            if (Massive[i][4] == 1) {
                Massive[i][3] = Massive[i][3] - 32;
            } else {
                if (Massive[i][4] == 2) {
                    Massive[i][2] = Massive[i][2] + 32;
                } else {
                    if (Massive[i][4] == 3) {
                        Massive[i][3] = Massive[i][3] + 32;
                    } else {
                        if (Massive[i][4] == 4) {
                            Massive[i][2] = Massive[i][2] - 32;
                        }
                    }
                }
            }
        }
        Massive[i][6] = 0;
    }

    private void Projectail(int i) {
        if (Massive[i][5] == 1) {
            for (int j = 1; j < 20; j++) {
                if (Projectail[i][j][1] == 0) {
                    Projectail[i][j][1] = 1;
                    Projectail[i][j][2] = Massive[i][2];
                    Projectail[i][j][3] = Massive[i][3];
                    Projectail[i][j][4] = Massive[i][4];
                    j = 21;
                }
            }
            Massive[i][5] = 0;
        }

        for (int j = 1; j < 21; j++) {
            if (Projectail[i][j][4] == 1) {
                Projectail[i][j][3] = Projectail[i][j][3] - 32;
                if (Projectail[i][j][3] == -32) {
                    Projectail[i][j][1] = 0;
                }
                for (int k = 1; k < 70; k++) {
                    if ((x[k] == Projectail[i][j][2]) && (y[k] == Projectail[i][j][3])) {
                        Projectail[i][j][1] = 0;
                    }
                }
            } else {
                if (Projectail[i][j][4] == 2) {
                    Projectail[i][j][2] = Projectail[i][j][2] + 32;
                    if (Projectail[i][j][2] == 672) {
                        Projectail[i][j][1] = 0;
                    }
                    for (int k = 1; k < 70; k++) {
                        if ((x[k] == Projectail[i][j][2]) && (y[k] == Projectail[i][j][3])) {
                            Projectail[i][j][1] = 0;
                        }
                    }
                } else {
                    if (Projectail[i][j][4] == 3) {
                        Projectail[i][j][3] = Projectail[i][j][3] + 32;
                        if (Projectail[i][j][3] == 672) {
                            Projectail[i][j][1] = 0;
                        }
                        for (int k = 1; k < 70; k++) {
                            if ((x[k] == Projectail[i][j][2]) && (y[k] == Projectail[i][j][3])) {
                                Projectail[i][j][1] = 0;
                            }
                        }
                    } else {
                        if (Projectail[i][j][4] == 4) {
                            Projectail[i][j][2] = Projectail[i][j][2] - 32;
                            if (Projectail[i][j][2] == -32) {
                                Projectail[i][j][1] = 0;
                            }
                            for (int k = 1; k < 70; k++) {
                                if ((x[k] == Projectail[i][j][2]) && (y[k] == Projectail[i][j][3])) {
                                    Projectail[i][j][1] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void Ban(int k){
        for(int j=1; j<21; j++){
            if((Massive[1][2]==Projectail[k][j][2]) && (Massive[1][3]==Projectail[k][j][3]) && (Projectail[k][j][1]==1)){
                Massive[1][1]=0;
                Projectail[k][j][1]=0;
            }
        }
    }

    private void Ban2(int k){
        for(int j=1; j<21; j++){
            if((Massive[k][2]==Projectail[1][j][2]) && (Massive[k][3]==Projectail[1][j][3]) && (Projectail[1][j][1]==1) && (Massive[k][1]==1)){
                Massive[k][1]=0;
                Projectail[1][j][1]=0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Massive[1][1]==1) {
            Gamer();
            Tank(1);
            Сollision(1);
        }
        Two Two = new Two();
        if(Massive[2][1]==1) {
            Two.start();
        }
        Three Three = new Three();
        if(Massive[3][1]==1) {
            Three.start();
        }
        Four Four = new Four();
        if(Massive[4][1]==1) {
            Four.start();
        }
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
            if(Massive[i][1]==1) {
                move(i);
            }
        }
        One2 One2 = new One2();
        One2.start();
        Two2 Two2 = new Two2();
        Two2.start();
        Three2 Three2 = new Three2();
        Three2.start();
        Four2 Four2 = new Four2();
        Four2.start();
        if(One2.isAlive()){
            try{
                One2.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        if(Two2.isAlive()){
            try{
                Two2.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        if(Three2.isAlive()){
            try{
                Three2.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        if(Four2.isAlive()){
            try{
                Four2.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException g){}
        }
        if(T==1){
            repaint();
        }
    }

    class Two extends Thread{
        int i = 2;
        @Override
        public void run(){
            bot(i);
            Tank(i);
            Сollision(i);
        }
    }

    class Three extends Thread{
        int i = 3;
        @Override
        public void run(){
            bot(i);
            Tank(i);
            Сollision(i);
        }
    }

    class Four extends Thread{
        int i = 4;
        @Override
        public void run(){
            bot(i);
            Tank(i);
            Сollision(i);
        }

    }

    class One2 extends Thread{
        int i = 1;
        @Override
        public void run() {
            Projectail(i);
            for (int k = 2; k < 5; k++) {
                Ban(k);
            }
        }
    }

    class Two2 extends Thread{
        int i = 2;
        @Override
        public void run(){
            Projectail(i);
            for (int k = 2; k < 5; k++) {
                Ban2(k);
            }
        }

    }

    class Three2 extends Thread{
        int i = 3;
        @Override
        public void run(){
            Projectail(i);
            for (int k = 2; k < 5; k++) {
                Ban2(k);
            }
        }

    }

    class Four2 extends Thread{
        int i = 4;
        @Override
        public void run(){
            Projectail(i);
            for (int k = 2; k < 5; k++) {
                Ban2(k);
            }
        }

    }

    // Отрисовка объектов
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if((Massive[1][1]==1) && ((Massive[2][1]==1) || (Massive[3][1]==1) || (Massive[4][1]==1))) {
            for (int i = 1; i < 70; i++) {
                g.drawImage(barikada, x[i], y[i], this);
            }
            for (int j = 1; j < 20; j++) {
                if (Projectail[1][j][1] == 1) {
                    g.drawImage(bomb, Projectail[1][j][2], Projectail[1][j][3], this);
                }
            }
            for (int i = 2; i < bots + 1; i++) {
                for (int j = 1; j < 20; j++) {
                    if (Projectail[i][j][1] == 1) {
                        g.drawImage(bomb2, Projectail[i][j][2], Projectail[i][j][3], this);
                    }
                }
            }

            for (int j = 1; j < bots + 1; j++) {
                if (Massive[j][1] == 1) {
                    g.drawImage(Image[j], Massive[j][2], Massive[j][3], this);
                }else{
                    if (Massive[j][1] == 0) {
                        g.drawImage(exposion,Massive[j][2], Massive[j][3],this);
                        Massive[j][1] = 2;
                    }
                }
            }
        }else {
            if (Massive[1][1]==0) {
                str = "YOU DIED";
            }else {
                str = "YOU WIN";
            }
            Font font = new Font("Serif", Font.ITALIC, 50);
            g.setFont(font);
            g.setColor(Color.red);
            g.drawString(str, 200, SIZE / 2);
            T=2;
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
