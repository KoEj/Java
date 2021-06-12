import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    static int APP_W = 800;
    static int APP_H = 800;
    int food_pos_x;
    int food_pos_y;

    Random random;

    Panel() {
        KeyAdapterListener keyAdapter = new KeyAdapterListener();
        setPreferredSize(new Dimension(APP_W,APP_H));
        setBackground(Color.gray);
        addKeyListener(new KeyAdapterListener());
        random = new Random();
        start();
    }

    public void start() {
        new_food();
        Timer timer = new Timer(75,this);
        timer.start();
    }

    public void paint(Graphics graphic) {
        super.paint(graphic);
        draw(graphic);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(100,100,25,25);
    }

    public void new_food() {
        food_pos_x = random.nextInt(APP_W);
        food_pos_y = random.nextInt(APP_H);
    }



    public void addKeyListener() {
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
