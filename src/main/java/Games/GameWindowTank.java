package Games;

import javax.swing.*;

public class GameWindowTank extends JFrame {
    public GameWindowTank() {
        setTitle("Игровое поле");
        setSize(352, 447);
        setLocation(300, 200);
        add(new GameFieldTank());
    }
}
