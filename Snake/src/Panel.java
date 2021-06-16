import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.lang.*;

public class Panel extends JPanel implements ActionListener {

    public int SPEED = 50;
    static int APP_W = 800;
    static int APP_H = 800;
    static int APP_U = 25;
    static int GAM_EL = (APP_W*APP_H)/APP_U;
    int score = 0;
    int food_pos_x;
    int food_pos_y;
    int thread_working = 1;
    int[] x = new int[GAM_EL];
    int[] y = new int[GAM_EL];
    int snake_el = 10;
    String direction = "DOWN";
    String running;

    Random random;
    Timer timer;
    Thread thread;

    Panel() {
        running = "YES";
        setPreferredSize(new Dimension(APP_W,APP_H));
        setBackground(Color.gray);
        random = new Random();
        setFocusable(true);
        MyKeyAdapter key_adapter = new MyKeyAdapter();
        addKeyListener(key_adapter);
        new_food();

        timer = new Timer(SPEED,this);
        timer.start();

        thread = new Thread(() ->
        {
            while(thread_working==1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SPEED = random.nextInt(50) + 50;
                System.out.println("check");
                timer.stop();
                timer = new Timer(SPEED, this);
                timer.start();
            }
        });

        thread.start();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        if("YES".equals(running)) {
            graphics.setColor(Color.red);
            graphics.fillOval(food_pos_x, food_pos_y, APP_U, APP_U);

            graphics.setColor(Color.blue);
            for (int i = 0; i < snake_el; i++) {
                graphics.fillRect(x[i], y[i], APP_U, APP_U);
            }
            graphics.setColor(Color.white);
            graphics.drawString("Wynik:"+score,APP_W-50,10);
        }
        else {
            setBackground(Color.black);
            graphics.setColor(Color.white);
            graphics.drawString("Przegrałeś!  Wynik:"+score,APP_W/2,APP_H/2);
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
        thread = new Thread(() ->
        {
            for (int i = snake_el; i > 0; i--) {
                if ((x[0] == x[i]) && (y[0] == y[i]) || x[0] < 0 || x[0] > APP_W ||
                        y[0] < 0 || y[0] > APP_H) {
                    running = "NO";
                    timer.stop();
                    thread_working = 0;
                    break;
                }
            }
        });
        //thread.sleep(10);
        thread.start();
    }

    public void check_food() {
        if((x[0]==food_pos_x && y[0]==food_pos_y)) {
            snake_el = snake_el + 1;
            score = score + 1;
            new_food();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if("YES".equals(running)) {
            move();
            check_food();
        }
        collision();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            switch(event.getKeyCode()) {
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (!"RIGHT".equals(direction)) {
                        direction = "LEFT";
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (!"LEFT".equals(direction)) {
                        direction = "RIGHT";
                    }
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (!"DOWN".equals(direction)) {
                        direction = "UP";
                    }
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (!"UP".equals(direction)) {
                        direction = "DOWN";
                    }
                    break;
            }
        }
    }

}


