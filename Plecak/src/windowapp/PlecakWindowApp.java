package windowapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlecakWindowApp extends JFrame {
    private JButton button1;
    private JPanel main_panel;
    private JTextField weight;
    private JTextField amount;
    private JTextArea result;
    private JTextField result_value;

    public PlecakWindowApp() {
        setContentPane(main_panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        weight.setText("");
        amount.setText("");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weight.getText()==null || weight.getText().equals("")) {
                    JOptionPane.showMessageDialog(PlecakWindowApp.this, "Brak dopuszczalnej wagi plecaka!");
                }
                if (amount.getText()==null || amount.getText().equals("")) {
                    JOptionPane.showMessageDialog(PlecakWindowApp.this, "Brak liczby wygenerowanych przedmiotów");
                }

                if (amount.getText().length()>0 && weight.getText().length()>0) {

                    int t1 = Integer.parseInt(amount.getText());
                    int t2 = Integer.parseInt(weight.getText());

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

                    result.setText(string_result);
                    result_value.setText(String.valueOf(BP.bp_value));
                }
            }
        });
    }
}
