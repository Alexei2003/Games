package games;

import javax.swing.JFrame;

public class GameWindowTank extends JFrame {
	public GameWindowTank() {
		setTitle("Игровое поле");
		setSize(655, 676);
		setLocation(100, 100);
		add(new GameFieldTank());
	}
}
