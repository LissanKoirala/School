import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // demo GUI
        System.out.println("SEQUENCE: Program started");
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI myGUI = new GUI(350, 150);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
