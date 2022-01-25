package zmeika;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Игровое поле");
        setSize(352, 447);
        setLocation(300, 200);
        add(new GameField());
    }
}
