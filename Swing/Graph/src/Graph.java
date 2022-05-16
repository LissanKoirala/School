import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {

    private JFrame frame;
    private int[] data;

    public Graph(int width, int height, int[] data) { // gets an array of data and graphs a histogram from it
        this.data = data;

        // initialise the window
        frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        // create the canvas that will hold the actual graphics
        frame.getContentPane().add(this);

        // display the frame AFTER adding the panel to prevent drawing glitches
        frame.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        int left = 50;    // hard-coded just for testing
        int top = 80;
        int width = 40;
        int height = 150;


        int number_of_data_points = data.length;
        int increments = (300-number_of_data_points*5)/number_of_data_points; //  (300-number_of_data_points*5) for space between the rectangles
        System.out.println(increments);


        for(int num: data){
            left = left+increments;
            g.setColor(Color.red);
            g.fillRect(left+5,top, width, num);
        }

    }
}

