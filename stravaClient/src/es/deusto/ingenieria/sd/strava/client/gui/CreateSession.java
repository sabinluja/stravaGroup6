package es.deusto.ingenieria.sd.strava.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSession extends JFrame {
    private JTextField titleField, distanceField, startDateField, startTimeField, durationField;
    private JComboBox<String> sportComboBox;
    private boolean dataProcessed = false;
    private String title, sport, startDate;
    private float distance;
    private long startTime;
    private int duration;

    public CreateSession() {
        // Configure the JFrame
        setTitle("Activity Data");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        // GUI components
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel sportLabel = new JLabel("Sport:");
        String[] sports = {"Cycling", "Running", "Both"};
        sportComboBox = new JComboBox<>(sports);
        JLabel distanceLabel = new JLabel("Distance:");
        distanceField = new JTextField();
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateField = new JTextField();
        JLabel startTimeLabel = new JLabel("Start Time (in seconds):");
        startTimeField = new JTextField();
        JLabel durationLabel = new JLabel("Duration (in minutes):");
        durationField = new JTextField();

        // Add components to the panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(sportLabel);
        panel.add(sportComboBox);
        panel.add(distanceLabel);
        panel.add(distanceField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(startTimeLabel);
        panel.add(startTimeField);
        panel.add(durationLabel);
        panel.add(durationField);

        // Add panel to the JFrame
        getContentPane().add(panel);
        
        JButton saveButton = new JButton("Save Data");
        getContentPane().add(saveButton, BorderLayout.SOUTH);

        // Make the JFrame visible
        setVisible(true);
    }

    // Methods to get processed data
    public boolean dataProcessed() {
        return dataProcessed;
    }

    public String getTitle() {
        return title;
    }

    public String getSport() {
        return sport;
    }

    public float getDistance() {
        return distance;
    }

    public String getStartDate() {
        return startDate;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

}