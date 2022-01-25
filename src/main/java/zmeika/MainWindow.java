package zmeika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JButton start;
    private JButton tutorial;
    private JButton exit;
    private JPanel panel;
    private GameWindow GW;
    private TutorialWindow TW;
    public MainWindow(){
        setTitle("Главное меню");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(150, 150);
        setLocation(700, 200);
        GW = new GameWindow();
        GW.setVisible(false);
        panel = new JPanel();
        start = new JButton("Начать игру");
        tutorial = new JButton("Обучение");
        exit = new JButton("Выход");

        // Нажатие кнопок
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GW.dispose();
                GW = new GameWindow();
                GW.setVisible(true);
            }
        });
        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TW = new TutorialWindow();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(start);
        panel.add(tutorial);
        panel.add(exit);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow MW = new MainWindow();
    }
}
