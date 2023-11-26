package es.deusto.ingenieria.sd.strava.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateChallenge extends JFrame {
    private JTextField nameField, startDateField, endDateField, targetDistanceField, targetTimeField;
    private JComboBox<String> sportComboBox;
    private boolean dataProcessed = false;
    private String name, startDate, endDate, sport;
    private float targetDistance;
    private long targetTime;

    public CreateChallenge() {
        // Configure the JFrame
        setTitle("Activity Data");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        // GUI components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField();
        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField();
        JLabel targetDistanceLabel = new JLabel("Target Distance:");
        targetDistanceField = new JTextField();
        JLabel targetTimeLabel = new JLabel("Target Time (in seconds):");
        targetTimeField = new JTextField();

        // Sport ComboBox
        JLabel sportLabel = new JLabel("Sport:");
        String[] sports = {"Cycling", "Running", "Both"};
        sportComboBox = new JComboBox<>(sports);

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(targetDistanceLabel);
        panel.add(targetDistanceField);
        panel.add(targetTimeLabel);
        panel.add(targetTimeField);
        panel.add(sportLabel);
        panel.add(sportComboBox);

        // Add panel to the JFrame
        getContentPane().add(panel);
        
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processData();
            }
        });
        getContentPane().add(saveButton, BorderLayout.SOUTH);

        // Make the JFrame visible
        setVisible(true);
    }
    
    private void processData() {
        // Get data from text fields and combo box
        name = nameField.getText();       
        startDate = startDateField.getText();
        endDate = endDateField.getText();       
        targetDistance = (float) Double.parseDouble(targetDistanceField.getText());
        targetTime = Long.parseLong(targetTimeField.getText());      
        sport = (String) sportComboBox.getSelectedItem();

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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public float getTargetDistance() {
        return targetDistance;
    }

    public long getTargetTime() {
        return targetTime;
    }

    public String getSport() {
        return sport;
    }

}