package Games;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeGame extends JFrame {
    private JButton Zmeika;
    private JButton Tank;
    private GameWindowZmeika GW;
    private JPanel panel;
    public ChangeGame() {
        setTitle("Выбор игры");
        setSize(300, 100);
        setLocation(700, 400);
        panel = new JPanel();
        Zmeika = new JButton("Змейка");
        Tank = new JButton("Танки");

        GW = new GameWindowZmeika();
        GW.setVisible(false);

        // Нажатие кнопок
        Zmeika.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GW.dispose();
                GW = new GameWindowZmeika();
                GW.setVisible(true);
            }
        });
        Tank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(Zmeika);
        panel.add(Tank);
        add(panel);
    }
}
