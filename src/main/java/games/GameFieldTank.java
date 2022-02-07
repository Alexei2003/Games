package games;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFieldTank extends JPanel implements ActionListener {
	private final int SIZE = 640;
	private final int ALL_DOTS = 400;
	private int bots = 4;
	private int[] x = new int[ALL_DOTS];
	private int[] y = new int[ALL_DOTS];
	private int[][] massive = new int[bots + 1][7];
	private int[][][] projectail = new int[bots + 1][21][5];
	private int n;
	private int temp1;
	private int temp2;
	private int t;
	private int t2;
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
	private Image[] image = new Image[bots + 1];
	private Timer timer;
	private Random rand = new Random();
	private String str;

	public GameFieldTank() {
		setBackground(Color.black);
		loadImages();
		initGame();
		timer();
		addKeyListener(new GameFieldTank.FieldKeyListener());
		setFocusable(true);
	}
	
	class FieldKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int key = e.getKeyCode();
			
			switch(key) {
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_SPACE:
				fire = true;
				break;
			default:
				System.out.println("Zhopa has happened");
				break;
			}
		}
	}
	
	class Base extends Thread {
		public int sus() {
			return 1;
		}

		@Override
		public void run() {
			bot(sus());
			tank(sus());
			collision(sus());
		}

		private void bot(int i) {
			if (t2 == 1) {
				n = rand.nextInt(2);
				if ((massive[1][2] - massive[i][2] > 0)) {
					temp1 = 2;
				} else {
					temp1 = 4;
				}
				if ((massive[1][3] - massive[i][3] > 0)) {
					temp2 = 3;
				} else {
					temp2 = 1;
				}
				if (n == 1) {
					if (massive[i][4] == temp1) {
						massive[i][6] = 1;
					}
					massive[i][4] = temp1;
				} else {
					if (massive[i][4] == temp2) {
						massive[i][6] = 1;
					}
					massive[i][4] = temp2;
				}
				if ((massive[1][2] - 32 <= massive[i][2]) && (massive[1][2] + 32 >= massive[i][2]) || (massive[1][3] - 32 <= massive[i][3]) && (massive[1][3] + 32 >= massive[i][3])) {
					massive[i][5] = 1;
				}
				t2 = 2;
			} else {
				t2 = 1;
				massive[i][6] = 1;
			}
		}

	}
	class Four extends Base {
		@Override
		public int sus() {
			return 4;
		}
	}
	class Three extends Base {
		@Override
		public int sus() {
			return 3;
		}
	}
	class Two extends Base {
		@Override
		public int sus() {
			return 2;
		}
	}
	class Base2 extends Thread {
		public int sus() {
			return 1;
		}
		
		@Override
		public void run() {
			projectail(sus());
			for (int k = 2; k < 5; k++) {
				ban2(k);
			}
		}

		private void ban2(int k) {
			for (int j = 1; j < 21; j++) {
				if ((massive[k][2] == projectail[1][j][2]) && (massive[k][3] == projectail[1][j][3]) && (projectail[1][j][1] == 1) && (massive[k][1] == 1)) {
					massive[k][1] = 0;
					projectail[1][j][1] = 0;
				}
			}
		}

	}
	class Four2 extends Base2 {
		@Override
		public int sus() {
			return 4;
		}
	}
	class One2 extends Base2 {
		@Override
		public void run() {
			projectail(sus());
			for (int k = 2; k < 5; k++) {
				ban(k);
			}
		}
		private void ban(int k) {
			for (int j = 1; j < 21; j++) {
				if ((massive[1][2] == projectail[k][j][2]) && (massive[1][3] == projectail[k][j][3]) && (projectail[k][j][1] == 1)) {
					massive[1][1] = 0;
					projectail[k][j][1] = 0;
				}
			}
		}
	}
	class Three2 extends Base2 {
		@Override
		public int sus() {
			return 3;
		}
	}
	class Two2 extends Base2 {
		@Override
		public int sus() {
			return 2;
		}
	}

	private void collision(int i) {
		for (int j = 1; j < 70; j++) {
			if ((massive[i][3] + 32 == y[j]) && (massive[i][2] == x[j]) && (massive[i][4] == 3)
				|| (massive[i][3] - 32 == y[j]) && (massive[i][2] == x[j]) && (massive[i][4] == 1)
				|| (massive[i][2] - 32 == x[j]) && (massive[i][3] == y[j]) && (massive[i][4] == 4)
				|| (massive[i][2] + 32 == x[j]) && (massive[i][3] == y[j]) && (massive[i][4] == 2)) {
				massive[i][6] = 0;
			}
		}
		for (int n = 2; n <= 3; n++) {
			if ((massive[i][n] + 32 == 640) && (massive[i][4] == n)) {
				massive[i][6] = 0;
			}
		}
		if ((massive[i][2] - 32 == 0) && (massive[i][4] == 4) || (massive[i][3] - 32 == 0) && (massive[i][4] == 1)) {
			massive[i][6] = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (massive[1][1] == 1) {
			gaymer();
			tank(1);
			collision(1);
		}
		Two two = new Two();
		if (massive[2][1] == 1) {
			two.start();
		}
		Three three = new Three();
		if (massive[3][1] == 1) {
			three.start();
		}
		Four four = new Four();
		if (massive[4][1] == 1) {
			four.start();
		}
		if (two.isAlive()) {
			try {
				two.join();
			} catch (InterruptedException g) {
				System.out.println("Zhopa has happened");
			}
		}
		if (three.isAlive()) {
			try {
				three.join();
			} catch (InterruptedException g) {
				System.out.println("Zhopa has happened");
			}
		}
		if (four.isAlive()) {
			try {
				four.join();
			} catch (InterruptedException g) {
				System.out.println("Zhopa has happened");
			}
		}
		for (int i = 1; i < bots + 1; i++) {
			if (massive[i][1] == 1) {
				move(i);
			}
		}
		One2 one2 = new One2();
		one2.start();
		Two2 two2 = new Two2();
		two2.start();
		Three2 three2 = new Three2();
		three2.start();
		Four2 four2 = new Four2();
		four2.start();
		if (one2.isAlive()) {
			try {
				one2.join();
			} catch (InterruptedException g) {
				System.out.println("Zhopa has happened");
			}
		}
		if (two2.isAlive()) {
			try {
				two2.join();
			} catch (InterruptedException e1) {
				System.out.println("Zhopa has happened");
			}
		}
		if (three2.isAlive()) {
			try {
				three2.join();
			} catch (InterruptedException g) {
				System.out.println("Zhopa has happened");
			}
		}
		if (four2.isAlive()) {
			try {
				four2.join();
			} catch (InterruptedException g) {
				System.out.println("Zhopa has happened");
			}
		}
		if (t == 1) {
			repaint();
		}
	}

	private void gaymer() {
		if (up) {
			if (massive[1][4] == 1) {
				massive[1][6] = 1;
			}
			massive[1][4] = 1;
			up = false;
		} else if (right) {
			if (massive[1][4] == 2) {
				massive[1][6] = 1;
			}
			massive[1][4] = 2;
			right = false;
		} else if (down) {
			if (massive[1][4] == 3) {
				massive[1][6] = 1;
			}
			massive[1][4] = 3;
			down = false;
		} else if (left) {
			if (massive[1][4] == 4) {
				massive[1][6] = 1;
			}
			massive[1][4] = 4;
			left = false;
		}

		if (fire) {
			massive[1][5] = 1;
			fire = false;
		}
	}

	private void initGame() {
		for (int k = 1; k <= 3; k++) {
			for (int l = 2; l <= 3; l++) {
				massive[k][l] = rand.nextInt(20) * 32;
			}
		}
		for (int i = 1; i < 70; i++) {
			x[i] = rand.nextInt(20) * 32;
			y[i] = rand.nextInt(20) * 32;
			for (int j = 1; j < i; j++) {
				if (((x[i] <= x[j] + 32) && (x[i] >= x[j] - 32) && (y[i] <= y[j] + 32) && (y[i] >= y[j] - 32))
						|| (x[i] == massive[1][2]) || (y[i] == massive[1][3])) {
					i = i - 1;
					j = 120;
				}
			}
		}
		for (int j = 1; j < bots + 1; j++) {
			for (int i = 1; i < 20; i++) {
				projectail[j][i][1] = 0;
				projectail[j][i][2] = -32;
				projectail[j][i][3] = -32;
				projectail[j][i][4] = 0;
			}
		}
		for (int j = 1; j < bots + 1; j++) {
			massive[j][1] = 1;
			massive[j][4] = 1;
			massive[j][6] = 1;
		}
		t = 1;
	}

	private void loadImages() {
		barikada = new ImageIcon("resources\\tank\\barikada.png").getImage();
		image[1] = new ImageIcon("resources\\tank\\tank1.png").getImage();
		tank1 = new ImageIcon("resources\\tank\\tank1.png").getImage();
		tank2 = new ImageIcon("resources\\tank\\tank2.png").getImage();
		tank3 = new ImageIcon("resources\\tank\\tank3.png").getImage();
		tank4 = new ImageIcon("resources\\tank\\tank4.png").getImage();
		bomb = new ImageIcon("resources\\tank\\fire.png").getImage();
		tank21 = new ImageIcon("resources\\tank\\tank21.png").getImage();
		tank22 = new ImageIcon("resources\\tank\\tank22.png").getImage();
		tank23 = new ImageIcon("resources\\tank\\tank23.png").getImage();
		tank24 = new ImageIcon("resources\\tank\\tank24.png").getImage();
		bomb2 = new ImageIcon("resources\\tank\\fire2.png").getImage();
		exposion = new ImageIcon("resources\\tank\\exposion.png").getImage();
	}

	private void move(int i) {
		if (massive[i][6] == 1) {
			switch(massive[i][4]) {
			case 1:
				massive[i][3] = massive[i][3] - 32;
				break;
			case 2:
				massive[i][2] = massive[i][2] + 32;
				break;
			case 3:
				massive[i][3] = massive[i][3] + 32;
				break;
			case 4:
				massive[i][2] = massive[i][2] - 32;
				break;
			default:
				System.out.println("Zhopa has happened");
				break;
			}
		}
		massive[i][6] = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if ((massive[1][1] == 1) && ((massive[2][1] == 1) || (massive[3][1] == 1) || (massive[4][1] == 1))) {
			for (int i = 1; i < 70; i++) {
				g.drawImage(barikada, x[i], y[i], this);
			}
			for (int j = 1; j < 20; j++) {
				if (projectail[1][j][1] == 1) {
					g.drawImage(bomb, projectail[1][j][2], projectail[1][j][3], this);
				}
			}
			for (int i = 2; i < bots + 1; i++) {
				for (int j = 1; j < 20; j++) {
					if (projectail[i][j][1] == 1) {
						g.drawImage(bomb2, projectail[i][j][2], projectail[i][j][3], this);
					}
				}
			}

			for (int j = 1; j < bots + 1; j++) {
				switch(massive[j][1]) {
				case 0:
					g.drawImage(exposion, massive[j][2], massive[j][3], this);
					massive[j][1] = 2;
					break;
				case 1:
					g.drawImage(image[j], massive[j][2], massive[j][3], this);
					break;
				default:
					System.out.println("Zhopa has happened");
					break;
				}
			}
		} else {
			if (massive[1][1] == 0) {
				str = "YOU DIED";
			} else {
				str = "YOU WIN";
			}
			Font font = new Font("Serif", Font.ITALIC, 50);
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString(str, 200, SIZE / 2);
			t = 2;
		}
	}

	private void projectail(int i) {
		if (massive[i][5] == 1) {
			for (int j = 1; j < 20; j++) {
				if (projectail[i][j][1] == 0) {
					projectail[i][j][1] = 1;
					projectail[i][j][2] = massive[i][2];
					projectail[i][j][3] = massive[i][3];
					projectail[i][j][4] = massive[i][4];
					j = 21;
				}
			}
			massive[i][5] = 0;
		}
		for (int j = 1; j < 21; j++) {
			switch(projectail[i][j][4]) {
			case 1:
				projectail[i][j][3] = projectail[i][j][3] - 32;
				if (projectail[i][j][3] == -32) {
					projectail[i][j][1] = 0;
				}
				for (int k = 1; k < 70; k++) {
					if ((x[k] == projectail[i][j][2]) && (y[k] == projectail[i][j][3])) {
						projectail[i][j][1] = 0;
						break;
					}
				}
				break;
			case 2:
				projectail[i][j][2] = projectail[i][j][2] + 32;
				if (projectail[i][j][2] == 672) {
					projectail[i][j][1] = 0;
				}
				for (int k = 1; k < 70; k++) {
					if ((x[k] == projectail[i][j][2]) && (y[k] == projectail[i][j][3])) {
						projectail[i][j][1] = 0;
						break;
					}
				}
				break;
			case 3:
				projectail[i][j][3] = projectail[i][j][3] + 32;
				if (projectail[i][j][3] == 672) {
					projectail[i][j][1] = 0;
				}
				for (int k = 1; k < 70; k++) {
					if ((x[k] == projectail[i][j][2]) && (y[k] == projectail[i][j][3])) {
						projectail[i][j][1] = 0;
						break;
					}
				}
				break;
			case 4:
				projectail[i][j][2] = projectail[i][j][2] - 32;
				if (projectail[i][j][2] == -32) {
					projectail[i][j][1] = 0;
				}
				for (int k = 1; k < 70; k++) {
					if ((x[k] == projectail[i][j][2]) && (y[k] == projectail[i][j][3])) {
						projectail[i][j][1] = 0;
						break;
					}
				}
				break;
			default:
				System.out.println("Zhopa has happened");
				break;
			}
		}
	}

	private void tank(int i) {
		HashMap<Integer, Image> map = new HashMap<>();
		switch(i) {
		case 1:
			map.put(1, tank1);
			map.put(2, tank2);
			map.put(3, tank3);
			map.put(4, tank4);
			image[i] = map.get(massive[1][4]);
			break;
		default:
			map.put(1, tank21);
			map.put(2, tank22);
			map.put(3, tank23);
			map.put(4, tank24);
			image[i] = map.get(massive[i][4]);
			break;
		}
	}

	private void timer() {
		timer = new Timer(500, this);
		timer.start();
	}
}
