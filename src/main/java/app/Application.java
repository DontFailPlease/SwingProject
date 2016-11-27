package app;

import javax.swing.*;
import gui.*;
import java.awt.*;

/**
 * Created by di on 23.11.16.
 */
public class Application {
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                new MainForm((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
            }
        });
    }
}
