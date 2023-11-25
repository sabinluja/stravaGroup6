package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean dataProcessed = false;
    private String username, password;

    public Login() {
        // Configure the JFrame
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // GUI components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameField.setText("sabin.luja@opendeusto.es");
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        getContentPane().add(loginButton, BorderLayout.SOUTH);

        // Button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processData();
            }
        });

        // Add components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Add panel to the JFrame
        getContentPane().add(panel);

        // Make the JFrame visible
        setVisible(true);
    }

    private void processData() {
        // Get data from text fields
        username = usernameField.getText();
        password = new String(passwordField.getPassword());

        // Indicate that data has been processed
        dataProcessed = true;

        // Close the window after processing data (adjust as needed)
        dispose();
    }

    // Methods to get processed data
    public boolean dataProcessed() {
        return dataProcessed;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
