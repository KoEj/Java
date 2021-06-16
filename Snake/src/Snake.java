import javax.swing.*;

public class Snake {
    public static void main(String[] args) {
        WindowApp app = new WindowApp();
    }

    public static class WindowApp extends JFrame {
        WindowApp() {
            Panel panel = new Panel();
            add(panel);
            setResizable(false);
            pack();
            setVisible(true);
        }

    }
}
