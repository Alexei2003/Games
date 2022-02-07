package games;

import javax.swing.JFrame;

public class GameWindowZmeika extends JFrame {
	public GameWindowZmeika() {
		setTitle("Игровое поле");
		setSize(352, 447);
		setLocation(300, 200);
		add(new GameFieldZmeika());
	}
}
