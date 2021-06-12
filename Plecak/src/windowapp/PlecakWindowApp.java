package windowapp;

import com.sun.tools.javac.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class PlecakWindowApp extends JFrame {
    private JButton generate;
    private JPanel main_panel;
    private JTextField weight;
    private JTextField amount;
    private JTextArea result;
    private JTextField result_value;
    private JButton sort;
    private JSlider slider1;
    private JSlider slider2;

    private void check() {
        if (weight.getText()==null || weight.getText().equals("")) {
            JOptionPane.showMessageDialog(PlecakWindowApp.this, "Brak dopuszczalnej wagi plecaka!");
        }
        if (amount.getText()==null || amount.getText().equals("")) {
            JOptionPane.showMessageDialog(PlecakWindowApp.this, "Brak liczby wygenerowanych przedmiotów");
        }
    }

    public PlecakWindowApp() {
        setContentPane(main_panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        weight.setText("");
        amount.setText("");

        slider1.setPaintTrack(true);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(50);
        slider1.setMinorTickSpacing(5);

        slider2.setPaintTrack(true);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(50);
        slider2.setMinorTickSpacing(5);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t1;
                int t2;
                //check();

                if (amount.getText().length() > 0 && weight.getText().length() > 0) {
                    t1 = Integer.parseInt(amount.getText());
                    t2 = Integer.parseInt(weight.getText());
                } else {
                    t1 = slider1.getValue();
                    t2 = slider2.getValue();
                }
                //check();
                backpack BP = new backpack(t1, t2);

                BP.init();
                BP.sort();
                BP.put_in();

                StringBuilder str_tab = new StringBuilder();

                for (int i = 0; i < BP.elements_n; i++) {
                    if (BP.inout.get(i) == 1) {
                        str_tab.append(BP.queue.get(i).toString());
                        str_tab.append(" ");
                        BP.queue.set(i, 1 + BP.queue.get(i));
                    }
                }

                String string_result = String.join("", str_tab);

                result.setText("");
                result.setText(string_result);
                result_value.setText(String.valueOf(BP.bp_value));
                //}
            }
        });


        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   try {
             //      playSound("./plecak.wav");
            }

        });
    }
}
