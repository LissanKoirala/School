import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // demo GUI
        System.out.println("SEQUENCE: Program started");
        JFrame frame = new JFrame("Demo frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI myGUI = new GUI(600, 400);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
