package games;

import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

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
	private int n = 0;
	private int minutesStart = 5;
	private int secondsStart = 0;
	private int minutes;
	private int seconds;
	private Timer timer;
	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;
	private boolean inGame = true;
	private String timer2 = "5.00";
	private String number2;
	private String min;
	private String sec;
	private Random rand = new Random();
	
	// Клавиши управленния
    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            switch(key) {
            case KeyEvent.VK_LEFT:
            	if (!right) {
                    left = true;
                    up = false;
                    down = false;
                }
            	break;
            case KeyEvent.VK_RIGHT:
            	if (!left){
                    right = true;
                    up = false;
                    down = false;
                }
            	break;
            case KeyEvent.VK_UP:
            	if (!down){
                    right = false;
                    up = true;
                    left = false;
                }
            	break;
            case KeyEvent.VK_DOWN:
            	if (!up){
                    right = false;
                    down = true;
                    left = false;
                }
            	break;
            }
        }
    }

	public GameFieldZmeika() {
		setBackground(Color.black);
		loadImages();
		initGame();
		timer();
		addKeyListener(new FieldKeyListener());
		setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (inGame) {
			checkBerryEaten(x[0], y[0], berryX1, berryY1, 1);
			checkBerryEaten(x[0], y[0], berryX2, berryY2, 2);
			checkBerryEaten(x[0], y[0], berryX3, berryY3, 3);
			checkCollisions();
			for (int i = dots + 2; i > 0; i--) {
				move(x[i - 1], y[i - 1], i);
			}
			horizontal(left, right);
			vertical(up, down);
			n++;
			timer(n, minutes, seconds);
		}
		repaint();
	}

	// Проверка появленния ягоды в змейке
	private void checkBerrySpawn(int x, int y, int berryX1, int berryY1, int berry) {
		if (berryX1 == x && berryY1 == y) {
			switch(berry) {
			case 1:
				createBerry1();
				break;
			case 2:
				createBerry2();
				break;
			case 3:
				createBerry3();
				break;
			}
		}
	}

	// Проверка поедания ягоды
	private void checkBerryEaten(int X, int Y, int berryX1, int berryY1, int berry) {
		if (X == berryX1 && Y == berryY1) {
			dots += berry;
			number += berry;
			switch(berry) {
			case 1:
				createBerry1();
				break;
			case 2:
				createBerry2();
				break;
			case 3:
				createBerry3();
				break;
			}
		}
	}

	// Проверка столкновений
	private void checkCollisions() {
		for (int i = dots - 1; i > 0; i--) {
			if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
				inGame = false;
				break;
			}
		}

		if (x[0] > SIZE || x[0] < 0 || y[0] > SIZE || y[0] < 0) {
			inGame = false;
		}
	}

	// Создание ягоды
	private void createBerry1() {
		berryX1 = rand.nextInt(20) * DOT_SIZE;
		berryY1 = rand.nextInt(20) * DOT_SIZE;
		for (int i = dots; i > 0; i--) {
			checkBerrySpawn(x[i], y[i], berryX1, berryY1, 1);
		}
		if (abs(berryX1 - berryX2) < DBB && abs(berryY1 - berryY2) < DBB) {
			createBerry1();
		}
		if (abs(berryX1 - berryX3) < DBB && abs(berryY1 - berryY3) < DBB) {
			createBerry1();
		}
	}

	// Создание ягоды
	private void createBerry2() {
		berryX2 = rand.nextInt(20) * DOT_SIZE;
		berryY2 = rand.nextInt(20) * DOT_SIZE;
		for (int i = dots; i > 0; i--) {
			checkBerrySpawn(x[i], y[i], berryX2, berryY2, 1);
		}
		if (abs(berryX2 - berryX1) < DBB && abs(berryY2 - berryY1) < DBB) {
			createBerry2();
		}
		if (abs(berryX2 - berryX3) < DBB && abs(berryY2 - berryY3) < DBB) {
			createBerry2();
		}
	}

	// Создание ягоды
	private void createBerry3() {
		berryX3 = rand.nextInt(20) * DOT_SIZE;
		berryY3 = rand.nextInt(20) * DOT_SIZE;
		for (int i = dots; i > 0; i--) {
			checkBerrySpawn(x[i], y[i], berryX3, berryY3, 1);
		}
		if (abs(berryX3 - berryX1) < DBB && abs(berryY3 - berryY1) < DBB) {
			createBerry3();
		}
		if (abs(berryX3 - berryX2) < DBB && abs(berryY3 - berryY2) < DBB) {
			createBerry3();
		}
	}

	// Изменнение положения змейки по горизонтали
	private void horizontal(boolean left, boolean right) {
		if (left) {
			x[0] -= DOT_SIZE;
		}
		if (right) {
			x[0] += DOT_SIZE;
		}
	}

	// Стартовые данные
	private void initGame() {
		dots = 3;
		for (int i = 0; i < dots + 2; i++) {
			x[i] = 96 - i * DOT_SIZE;
			y[i] = 48;
		}
		minutes = minutesStart;
		seconds = secondsStart;
	}

	// Подгрузка изображенний
	private void loadImages() {
		berry1 = new ImageIcon("resources\\zmeika\\Berry1.png").getImage();
		berry2 = new ImageIcon("resources\\zmeika\\Berry2.png").getImage();
		berry3 = new ImageIcon("resources\\zmeika\\Berry3.png").getImage();
		dot = new ImageIcon("resources\\zmeika\\dot.png").getImage();
	}

	// Перемещение змейки
	private void move(int PredX, int PredY, int i) {
		x[i] = PredX;
		y[i] = PredY;
	}

	// Отрисовка объектов
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawLine(0, 352, 352, 352);
		Font font = new Font("Serif", Font.ITALIC, 50);
		g.setFont(font);
		number2 = String.valueOf(number * 100);
		g.drawString(number2, 10, 402);
		min = String.valueOf(minutes);
		sec = String.valueOf(seconds);
		if (seconds < 10) {
			timer2 = min + ".0" + sec;
		} else {
			timer2 = min + "." + sec;
		}
		g.drawString(timer2, 230, 402);
		if (inGame) {
			g.drawImage(berry1, berryX1, berryY1, this);
			g.drawImage(berry2, berryX2, berryY2, this);
			g.drawImage(berry3, berryX3, berryY3, this);
			for (int i = 0; i < dots; i++) {
				g.drawImage(dot, x[i], y[i], this);
			}
		} else {
			String str = "YOU DIED";
			g.setColor(Color.red);
			g.drawString(str, 50, SIZE / 2);
		}
	}

	// Обновленние окна
	private void timer() {
		timer = new Timer(250, this);
		timer.start();
		createBerry1();
		createBerry2();
		createBerry3();
	}

	// Таймер, оставшегося времени
	private void timer(int n1, int min, int sec) {
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

	// �?зменнение положения змейки по вертикали
	private void vertical(boolean up, boolean down) {
		if (up) {
			y[0] -= DOT_SIZE;
		}
		if (down) {
			y[0] += DOT_SIZE;
		}
	}
}
