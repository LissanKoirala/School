import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener{
    // canvas for other GUI widgets

    String username = "admin";
    String password = "admin";

    JButton button;
    JLabel usernameLabel;
    JTextField usernameTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;

    public GUI(int width, int height) {
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40,20, 80, 25);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(130,20, 165, 25);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40,50, 80, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(130,50, 165, 25);

        button = new JButton("Submit");
        button.addActionListener(this);
        button.setBounds(40,85, 80, 25);

        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String inputUsername = usernameTextField.getText();
        String inputPassword = passwordField.getText();

        if (inputUsername.equals(username) && inputPassword.equals(password)){
            System.out.print("User Authenticated...");

        } else { // if authentication credentials invalid...
            JOptionPane.showMessageDialog(this, "Invalid Credentials, Try Again");
            passwordField.setText(""); // Clears the password field...
        }

    }
}