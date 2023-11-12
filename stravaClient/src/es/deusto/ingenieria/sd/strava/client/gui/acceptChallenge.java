package es.deusto.ingenieria.sd.strava.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class acceptChallenge extends JFrame {
    private JTextField nameField;
    private boolean dataProcessed = false;
    private String name;

    public acceptChallenge() {
        // Configure the JFrame
        setTitle("Activity Data");
        setSize(454, 234);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        // GUI components
        JLabel nameLabel = new JLabel("Challenge Name:");
        nameField = new JTextField();

        // Button action
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processData();
            }
        });

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(saveButton);

        // Add panel to the JFrame
        getContentPane().add(panel);

        // Make the JFrame visible
        setVisible(true);
    }

    private void processData() {
        // Get data from text fields and combo box
        name = nameField.getText();
        

        // Indicate that data has been processed
        dataProcessed = true;

        // Close the window after processing data (adjust as needed)
        dispose();
    }

    // Methods to get processed data
    public boolean dataProcessed() {
        return dataProcessed;
    }

    public String getName() {
        return name;
    }


}