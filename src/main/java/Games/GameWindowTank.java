package Games;

import javax.swing.*;

public class GameWindowTank extends JFrame {
    public GameWindowTank() {
        setTitle("Игровое поле");
        setSize(652, 652);
        setLocation(100, 100);
        add(new GameFieldTank());
    }
}
