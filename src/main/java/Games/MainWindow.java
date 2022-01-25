package Games;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JButton start;
    private JButton exit;
    private JPanel panel;
    private ChangeGame CG;
    public MainWindow(){
        setTitle("Главное меню");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(150, 125);
        setLocation(700, 200);
        panel = new JPanel();
        start = new JButton("Выбрать игру");
        exit = new JButton("Выход");

        CG = new ChangeGame();
        CG.setVisible(false);

        // Нажатие кнопок
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CG.dispose();
                CG = new ChangeGame();
                CG.setVisible(true);
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

    public static void main(String[] args) {
        MainWindow MW = new MainWindow();
    }
}
