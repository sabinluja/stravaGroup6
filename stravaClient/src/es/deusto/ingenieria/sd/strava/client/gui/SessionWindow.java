package es.deusto.ingenieria.sd.strava.client.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import es.deusto.ingenieria.sd.strava.client.controller.SessionController;

public class SessionWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public SessionWindow(SessionController controller) { // Change constructor parameter
        // Obtain the methods of the controller using reflection
        List<Method> methods = Arrays.asList(SessionController.class.getMethods()); // Change here

        Vector<String> methodNames = new Vector<>();
        methods.forEach(method -> {
            if (method.getName().contains("Session")) {
                methodNames.add(method.getName());
            }
        });

        Collections.sort(methodNames);

        JList<String> endpointsJList = new JList<>(methodNames);
        endpointsJList.setBorder(new TitledBorder("Endpoints"));
        endpointsJList.setSelectedIndex(-1);
        endpointsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        endpointsJList.setPreferredSize(new Dimension(200, 200));

        JTextPane endpointResults = new JTextPane();
        endpointResults.setBackground(new Color(0, 40, 51));
        endpointResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(endpointResults);
        scrollPane.setBorder(new TitledBorder("Server responses"));

        SimpleAttributeSet orangeText = new SimpleAttributeSet();
        StyleConstants.setForeground(orangeText, Color.ORANGE);

        SimpleAttributeSet greenText = new SimpleAttributeSet();
        StyleConstants.setForeground(greenText, new Color(133, 153, 1));

        SimpleAttributeSet grayText = new SimpleAttributeSet();
        StyleConstants.setForeground(grayText, new Color(131, 148, 149));
        StyleConstants.setBold(grayText, true);
        
        JButton back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setBounds(10, 504, 89, 23);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SessionWindow.this.dispose();
            }
        });
        getContentPane().add(back);

        // Update the lister for the JList to use SessionController
        endpointsJList.addListSelectionListener(e -> {
            String selectedValue = endpointsJList.getSelectedValue();

            StyledDocument resultsDoc = endpointResults.getStyledDocument();

            if (selectedValue != null && e.getValueIsAdjusting()) {
                try {
                    resultsDoc.insertString(resultsDoc.getLength(), selectedValue + "\n", grayText);

                    Class<? extends SessionController> SessionControllerClass = controller.getClass();
                    
                    if (selectedValue.contains("create")) {
                        CreateSession s = new CreateSession();
                        if (s.dataProcessed()) {
                        	String name = s.getName();
                    		String startDate = s.getStartDate();
                    		long startTime = s.getStartTime();
                    		String sport = s.getSport();
                    	    float distance = s.getDistance();
                    	    int duration = s.getDuration();
                    	    System.out.println(name);
                    	    System.out.println(startDate);
                    	    System.out.println(startTime);
                    	    System.out.println(sport);
                    	    System.out.println(distance);
                    	    System.out.println(duration);
                        }
                    }
                    
                    // Getting method by name
                    Method method = SessionControllerClass.getMethod(selectedValue);
                    // Method invocation using reflection
                    Object result = method.invoke(controller);

                    if (result != null) {
                        resultsDoc.insertString(resultsDoc.getLength(), result.toString() + "\n\n", greenText);
                    }

                } catch (Exception ex1) {
                    try {
                        resultsDoc.insertString(resultsDoc.getLength(),
                                " - The invocation throws an exception: " + ex1.getMessage() + "\n\n", orangeText);
                    } catch (Exception ex2) {
                    }
                }
            }
        });

        this.setTitle("SpringBoot Session Application GUI");
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(endpointsJList, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(1024, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
}
