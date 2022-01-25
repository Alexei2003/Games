package zmeika;

import javax.swing.*;
import java.awt.*;

public class TutorialWindow extends JFrame {
    public TutorialWindow(){
        setTitle("Обучение");
        setSize(510,350);
        setLocation(200,200);
        setBackground(Color.black);
        add(new Tutorial());
        setVisible(true);

    }
}
