import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    static int APP_W = 800;
    static int APP_H = 800;
    static int APP_U = 25;
    static int GAM_EL = (APP_W*APP_H)/APP_U;
    int food_pos_x;
    int food_pos_y;
    int[] x = new int[GAM_EL];
    int[] y = new int[GAM_EL];
    int snake_el = 10;
    String direction = "RIGHT";
    String running = "YES";

    Random random;

    Panel() {
        KeyAdapterListener keyAdapter = new KeyAdapterListener();
        setPreferredSize(new Dimension(APP_W,APP_H));
        //setBackground(Color.gray);
        addKeyListener(new KeyAdapterListener());
        random = new Random();
        start();
    }

    public void start() {
        new_food();
        Timer timer = new Timer(75,this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {

//        for(int i=0;i<APP_H/APP_U;i++) {
//            graphics.drawLine(i*APP_U,0,i*APP_U,APP_H);
//            graphics.drawLine(0,i*APP_U,APP_W,i*APP_U);
//        }

        graphics.setColor(Color.red);
        graphics.fillOval(food_pos_x,food_pos_y,APP_U,APP_U);

        graphics.setColor(Color.blue);
        for(int i=0;i<snake_el;i++) {
            graphics.fillRect(x[i],y[i],APP_U,APP_U);
        }
    }

    public void new_food() {
        food_pos_x = random.nextInt(APP_W/APP_U)*APP_U;
        food_pos_y = random.nextInt(APP_H/APP_U)*APP_U;
    }

    public void move() {
        for (int i=snake_el;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if ("LEFT".equals(direction)) {
            x[0] = x[0] - APP_U;
        }
        if ("RIGHT".equals(direction)) {
            x[0] = x[0] + APP_U;
        }
        if ("UP".equals(direction)) {
            y[0] = y[0] - APP_U;
        }
        if ("DOWN".equals(direction)) {
            y[0] = y[0] + APP_U;
        }
    }

    public void collision() {
        for(int i=snake_el;i>0;i--) {
            if((x[0]==x[i]) && (y[0]==y[i])) {
                running = "NO";
                break;
            }
        }
    }

    public void addKeyListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("YES".equals(running)) {
            move();

        }
        repaint();
    }
}
