package games;

import javax.swing.*;

public class GameWindowMaincraft extends JFrame {
    public GameWindowMaincraft() {
        setTitle("Игровое поле");
        setSize(1920, 1080);
        setLocation(0, 0);
        add(new GameFieldMaincraft());
    }
}
