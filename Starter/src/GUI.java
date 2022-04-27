
import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    // canvas for other GUI widgets

    JButton button1;
    JTextField textfield;

    public GUI(int width, int height) {
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);
        button1 = new JButton("b1");
        button1.setBounds(0,0, 100, 40);


        textfield = new JTextField();
        textfield.setBounds(120,0, 100, 30);

        add(button1);
        add(textfield);
    }
}