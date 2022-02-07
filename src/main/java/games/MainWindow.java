package games;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {
	private JButton start;
	private JButton exit;
	private JPanel panel;
	private ChangeGame cg;
	
	public static void main(String[] args) {
		new MainWindow();
	}

	public MainWindow() {
		setTitle("������� ����");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(150, 125);
		setLocation(700, 200);
		panel = new JPanel();
		start = new JButton("Start game");
		exit = new JButton("Exit");

		cg = new ChangeGame();
		cg.setVisible(false);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cg.dispose();
				cg = new ChangeGame();
				cg.setVisible(true);
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(start);
		panel.add(exit);
		add(panel);
		setVisible(true);
	}
}