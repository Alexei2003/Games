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
    private int[] [] Projectail2 = new int[21][5];
    private int X;
    private int Y;
    private int N;
    private int X2;
    private int Y2;
    private int N2;
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
    private boolean fire2 = false;
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
        X = rand.nextInt(20)*32;
        Y = rand.nextInt(20)*32;
        X2 = rand.nextInt(20)*32;
        Y2 = rand.nextInt(20)*32;
        for (int i = 1; i < 70; i++){
            x[i] = rand.nextInt(20)*32;
            y[i] = rand.nextInt(20)*32;
            for (int j = 1; j < i; j++){
                if (((x[i] <= x[j]+32) && (x[i] >= x[j]-32) && (y[i] <= y[j]+32) && (y[i] >= y[j]-32)) || (x[i] == X) || (y[i] == Y)){
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
        for(int i = 1; i<20; i++){
            Projectail2[i][1] = 0;
            Projectail2[i][2] = - 32;
            Projectail2[i][3] = - 32;
            Projectail2[i][4] = 0;
        }
        T2=1;
    }

    private void timer() {
        timer = new Timer(250, this);
        timer.start();
    }

    private  void bot2(){
        if(T2==1){
            N=rand.nextInt(2);
            if((X-X2>0)){
                Temp1=2;
            }else{
                Temp1=4;
            }
            if((Y-Y2>0)){
                Temp2=3;
            }else{
                Temp2=1;
            }
            if(N==1){
                N2=Temp1;
            }else{
                N2=Temp2;
            }
            T2=2;
        }else{
            T2=1;
        }
    }

    private void Rotation(){
        if (upF){
            tank5 = tank1;
        }else{
            if (rightF){
                tank5 = tank2;
            }else{
                if (downF){
                    tank5 = tank3;
                }else{
                    if (leftF){
                        tank5 = tank4;
                    }
                }
            }
        }
    }

    private void Rotation2(){
        if (N2==1){
            tank25 = tank21;
        }else{
            if (N2==2){
                tank25 = tank22;
            }else{
                if (N2==3){
                    tank25 = tank23;
                }else{
                    if (N2==4){
                        tank25 = tank24;
                    }
                }
            }
        }
    }

    private void move(){
        if (up){
            Y = Y -32;
            up = false;
        }else{
            if (right){
                X = X+32;
                right = false;
            }else{
                if (down){
                    Y = Y +32;
                    down  = false;
                }else{
                    if (left){
                        X = X-32;
                        left = false;
                    }
                }
            }
        }
    }

    private void move2(){
        if ((N2==1) && (T2==1)){
            Y2 = Y2 -32;
        }else{
            if ((N2==2) && (T2==1)){
                X2 = X2+32;
            }else{
                if ((N2==3) && (T2==1)){
                    Y2 = Y2 +32;
                }else{
                    if ((N2==4) && (T2==1)){
                        X2 = X2-32;
                    }
                }
            }
        }
    }

    private void CheckColision(){
        for(int i=1; i<70; i++){
            if ((X+32 == x[i]) && (Y == y[i]) && (right)){
                right = false;
            }
            if ((X-32 == x[i]) && (Y == y[i]) && (left)){
                left = false;
            }
            if ((Y +32 == y[i]) && (X == x[i]) && (down)){
                down = false;
            }
            if ((Y -32 == y[i]) && (X == x[i]) && (up)){
                up = false;
            }
        }
        if ((X+32 == 640)&& (right)){
            right = false;
        }
        if ((X-32 == 0) && (left)){
            left = false;
        }
        if ((Y +32 == 640) && (down)){
            down = false;
        }
        if ((Y -32 == 0) && (up)){
            up = false;
        }
    }

    private void CheckColision2(){
        for(int i=1; i<70; i++){
            if ((X2+32 == x[i]) && (Y2 == y[i]) && (N2==2)){
                N2=0;
            }
            if ((X2-32 == x[i]) && (Y2 == y[i]) && (N2==4)){
                N2=0;
            }
            if ((Y2 +32 == y[i]) && (X2 == x[i]) && (N2==3)){
                N2=0;
            }
            if ((Y2 -32 == y[i]) && (X2 == x[i]) && (N2==1)){
                N2=0;
            }
        }
        if ((X2+32 == 640)&& (N2==2)){
            N2=0;
        }
        if ((X2-32 == 0) && (N2==4)){
            N2=0;
        }
        if ((Y2+32 == 640) && (N2==3)){
            N2=0;
        }
        if ((Y2-32 == 0) && (N2==1)){
            N2=0;
        }
    }

    private void Projectail(){
        if (fire){
            for(int i=1; i<20; i++){
                if (Projectail[i][1]==0){
                    if (upF){
                        Projectail[i][1]=1;
                        Projectail[i][2]=X;
                        Projectail[i][3]= Y;
                        Projectail[i][4]=1;
                        i=21;
                    }else{
                        if (rightF){
                            Projectail[i][1]=1;
                            Projectail[i][2]=X;
                            Projectail[i][3]= Y;
                            Projectail[i][4]=2;
                            i=21;
                        }else{
                            if (downF){
                                Projectail[i][1]=1;
                                Projectail[i][2]=X;
                                Projectail[i][3]= Y;
                                Projectail[i][4]=3;
                                i=21;
                            }else{
                                if (leftF){
                                    Projectail[i][1]=1;
                                    Projectail[i][2]=X;
                                    Projectail[i][3]= Y;
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

    private void User2(){
        if((X-1 <= X2) && (X+1 >= X2) || (Y-1 <= Y2) && (Y+1 >= Y2)){
            fire2=true;
        }
    }

    private void Projectail2(){
        if (fire2 && (T2==1)){
            for(int i=1; i<20; i++){
                if (Projectail2[i][1]==0){
                    if (N2==1){
                        Projectail2[i][1]=1;
                        Projectail2[i][2]=X2;
                        Projectail2[i][3]= Y2;
                        Projectail2[i][4]=1;
                        i=21;
                    }else{
                        if (N2==2){
                            Projectail2[i][1]=1;
                            Projectail2[i][2]=X2;
                            Projectail2[i][3]= Y2;
                            Projectail2[i][4]=2;
                            i=21;
                        }else{
                            if (N2==3){
                                Projectail2[i][1]=1;
                                Projectail2[i][2]=X2;
                                Projectail2[i][3]= Y2;
                                Projectail2[i][4]=3;
                                i=21;
                            }else{
                                if (N2==4){
                                    Projectail2[i][1]=1;
                                    Projectail2[i][2]=X2;
                                    Projectail2[i][3]= Y2;
                                    Projectail2[i][4]=4;
                                    i=21;
                                }
                            }
                        }
                    }
                }
            }
            fire2=false;
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

    private void MoveProjectail2(){
        for(int i=1; i<21; i++){
            if(Projectail2[i][4] == 1){
                Projectail2[i][3]=Projectail2[i][3]-32;
                if(Projectail2[i][3]==-32){
                    Projectail2[i][1]=0;
                }
                for(int j=1; j<70; j++){
                    if((x[j]==Projectail2[i][2]) && (y[j]==Projectail2[i][3])){
                        Projectail2[i][1]=0;
                    }
                }
            }else{
                if(Projectail2[i][4]== 2){
                    Projectail2[i][2]=Projectail2[i][2]+32;
                    if(Projectail2[i][2]==672){
                        Projectail2[i][1]=0;
                    }
                    for(int j=1; j<70; j++){
                        if((x[j]==Projectail2[i][2]) && (y[j]==Projectail2[i][3])){
                            Projectail2[i][1]=0;
                        }
                    }
                }else{
                    if(Projectail2[i][4]== 3){
                        Projectail2[i][3]=Projectail2[i][3]+32;
                        if(Projectail2[i][3]==672){
                            Projectail2[i][1]=0;
                        }
                        for(int j=1; j<70; j++){
                            if((x[j]==Projectail2[i][2]) && (y[j]==Projectail2[i][3])){
                                Projectail2[i][1]=0;
                            }
                        }
                    }else{
                        if(Projectail2[i][4]== 4){
                            Projectail2[i][2]=Projectail2[i][2]-32;
                            if(Projectail2[i][2]==-32){
                                Projectail2[i][1]=0;
                            }
                            for(int j=1; j<70; j++){
                                if((x[j]==Projectail2[i][2]) && (y[j]==Projectail2[i][3])){
                                    Projectail2[i][1]=0;
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
        bot2();
        CheckColision2();
        Rotation2();
        move2();
        User2();
        Projectail2();
        MoveProjectail2();
        repaint();
    }

    // Отрисовка объектов
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=1; i < 70; i++){
            g.drawImage(barikada, x[i], y[i], this);
        }
        g.drawImage(tank5, X, Y, this);
        g.drawImage(tank25, X2, Y2, this);
        for(int i=1; i<20; i++){
            if(Projectail[i][1] == 1){
                g.drawImage(bomb,Projectail[i][2],Projectail[i][3],this);
            }
        }
        for(int i=1; i<20; i++){
            if(Projectail2[i][1] == 1){
                g.drawImage(bomb2,Projectail2[i][2],Projectail2[i][3],this);
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
