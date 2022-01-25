package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import static java.lang.Math.abs;

public class GameFieldZmeika extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private final int DBB = 4 * DOT_SIZE;
    private Image dot;
    private Image berry1;
    private Image berry2;
    private Image berry3;
    private int berryX1 = 0;
    private int berryY1 = 0;
    private int berryX2 = 0;
    private int berryY2 = 0;
    private int berryX3 = 0;
    private int berryY3 = 0;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private int number = 0;
    private int speadUp = 10;
    private int n = 0;
    private int minutesStart = 3;
    private int secondsStart = 0;
    private int minutes;
    private int seconds;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private boolean Finish = true;
    private String Timer = "3.00";
    private String Number;
    private String Minutes;
    private String Seconds;

    public GameFieldZmeika() {
        setBackground(Color.black);
        loadImages();
        initGame();
        timer();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    // Стартовые данные
    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots + 2; i++) {
            x[i] = 96 - i * DOT_SIZE;
            y[i] = 48;
        }
        minutes = minutesStart;
        seconds = secondsStart;
    }

    // Обновленние окна
    public void timer() {
        timer = new Timer(250, this);
        timer.start();
        createBerry1();
        createBerry2();
        createBerry3();
    }

    // Проверка появленния ягоды в змейке
    public void berry1(int x, int y,int berryX1,int berryY1){
        if (berryX1 == x && berryY1 == y) {
            createBerry1();
        }
    }

    // Создание ягоды
    public void createBerry1() {
        berryX1 = new Random().nextInt(20) * DOT_SIZE;
        berryY1 = new Random().nextInt(20) * DOT_SIZE;
        for (int i = dots; i > 0; i--) {
            berry1(x[i],y[i],berryX1,berryY1);
        }
        if (abs(berryX1 - berryX2) < DBB && abs(berryY1 - berryY2) < DBB) {
            createBerry1();
        }
        if (abs(berryX1 - berryX3) < DBB && abs(berryY1 - berryY3) < DBB) {
            createBerry1();
        }
    }

    // Проверка появленния ягоды в змейке
    public void berry2(int x, int y,int berryX2,int berryY2){
        if (berryX2 == x && berryY2 == y) {
            createBerry2();
        }
    }

    // Создание ягоды
    public void createBerry2() {
        berryX2 = new Random().nextInt(20) * DOT_SIZE;
        berryY2 = new Random().nextInt(20) * DOT_SIZE;
        for (int i = dots; i > 0; i--) {
            berry2(x[i],y[i],berryX2,berryY2);
        }
        if (abs(berryX2 - berryX1) < DBB && abs(berryY2 - berryY1) < DBB) {
            createBerry2();
        }
        if (abs(berryX2 - berryX3) < DBB && abs(berryY2 - berryY3) < DBB) {
            createBerry2();
        }
    }

    // Проверка появленния ягоды в змейке
    public void berry3(int x, int y,int berryX3,int berryY3){
        if (berryX3 == x && berryY3 == y) {
            createBerry3();
        }
    }

    // Создание ягоды
    public void createBerry3() {
        berryX3 = new Random().nextInt(20) * DOT_SIZE;
        berryY3 = new Random().nextInt(20) * DOT_SIZE;
        for (int i = dots; i > 0; i--) {
            berry3(x[i],y[i],berryX3,berryY3);
        }
        if (abs(berryX3 - berryX1) < DBB && abs(berryY3 - berryY1) < DBB) {
            createBerry3();
        }
        if (abs(berryX3 - berryX2) < DBB && abs(berryY3 - berryY2) < DBB) {
            createBerry3();
        }
    }

    // Подгрузка изображенний
    public void loadImages() {
        ImageIcon iib1 = new ImageIcon("resources\\zmeika\\Berry1.png");
        berry1 = iib1.getImage();
        ImageIcon iib2 = new ImageIcon("resources\\zmeika\\Berry2.png");
        berry2 = iib2.getImage();
        ImageIcon iib3 = new ImageIcon("resources\\zmeika\\Berry3.png");
        berry3 = iib3.getImage();
        ImageIcon iid = new ImageIcon("resources\\zmeika\\dot.png");
        dot = iid.getImage();
    }

    // Отрисовка объектов
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawLine(0, 352, 352, 352);
        Font font = new Font("Serif", Font.ITALIC, 50);
        g.setFont(font);
        Number = String.valueOf(number * 100);
        g.drawString(Number, 10, 402);
        Minutes = String.valueOf(minutes);
        Seconds = String.valueOf(seconds);
        if (seconds < 10) {
            Timer = Minutes + ".0" + Seconds;
        } else {
            Timer = Minutes + "." + Seconds;
        }
        g.drawString(Timer, 230, 402);
        if (inGame) {
            g.drawImage(berry1, berryX1, berryY1, this);
            g.drawImage(berry2, berryX2, berryY2, this);
            g.drawImage(berry3, berryX3, berryY3, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str, 50, SIZE / 2);
        }
    }

    // Таймер, оставшегося времени
    public void Timer(int n1,int min,int sec) {
        if (n1 > 4) {
            n1 = 1;
            if (sec == 0) {
                min = min - 1;
                sec = 60;
            }
            sec--;
            if ((min == 0) && (sec == 0)) {
                inGame = false;
            }
            n = n1;
            minutes = min;
            seconds = sec;
        }
    }

    // Перемещение змейки
    public void move(int PredX, int PredY,int i) {
            x[i] = PredX;
            y[i] = PredY;
    }

    // Изменнение положения змейки по горизонтали
    public void horizontal(boolean left, boolean right) {
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
    }

    // Изменнение положения змейки по вертикали
    public void vertical(boolean up, boolean down){
        if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    // Проверка поедания ягоды
    public void checkBerry1(int X, int Y,int berryX1,int berryY1) {
        if (X == berryX1 && Y == berryY1) {
            dots = dots + 1;
            number = number + 1;
            createBerry1();
        }
    }

    // Проверка поедания ягоды
    public void checkBerry2(int X,int Y,int berryX2,int berryY2) {
        if (X == berryX2 && Y == berryY2) {
            dots = dots + 2;
            number = number + 2;
            createBerry2();
        }
    }

    // Проверка поедания ягоды
    public void checkBerry3(int X, int Y, int berryX3,int berryY3) {
        if(X == berryX3 && Y == berryY3){
            dots = dots +3;
            number = number + 3;
            createBerry3();
        }
    }

    // Проверка столкновений
    public void checkCollisions(){
        for (int i = dots-1; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        if(x[0]>SIZE){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkBerry1(x[0],y[0],berryX1,berryY1);
            checkBerry2(x[0],y[0],berryX2,berryY2);
            checkBerry3(x[0],y[0],berryX3,berryY3);
            checkCollisions();
            for (int i = dots + 2; i > 0; i--) {
                move(x[i-1],y[i-1],i);
            }
            horizontal(left, right);
            vertical(up, down);
            n++;
            Timer(n,minutes,seconds);
        }
        repaint();
    }

    // Клавиши управленния
    class FieldKeyListener extends KeyAdapter{
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
