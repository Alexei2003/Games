package games;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChangeGame extends JFrame {
	private JButton zmeika;
	private JButton tank;
	private JButton maincraft;
	private JFrame window;
	private JPanel panel;

	public ChangeGame() {
		setTitle("Change game");
		setSize(600, 100);
		setLocation(700, 400);
		panel = new JPanel();
		zmeika = new JButton("Zmeika");
		tank = new JButton("Tank");
		maincraft = new JButton("MAINCRAFT IS MY LIFE");

		window = new JFrame();
		window.setVisible(false);

		zmeika.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				window = new GameWindowZmeika();
				window.setVisible(true);
			}
		});
		tank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				window = new GameWindowTank();
				window.setVisible(true);
			}
		});
		maincraft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				window = new GameWindowMaincraft();
				window.setVisible(true);
			}
		});

		panel.add(maincraft);
		panel.add(zmeika);
		panel.add(tank);
		add(panel);
	}
}
