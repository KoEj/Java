import javax.swing.*;

public class WindowApp extends JFrame {
    WindowApp() {
        Panel panel = new Panel();

        add(panel);
        setResizable(false);
        pack();
        setVisible(true);
    }

}
