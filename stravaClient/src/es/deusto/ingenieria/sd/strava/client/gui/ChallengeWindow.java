package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import es.deusto.ingenieria.sd.strava.client.controller.ChallengeController;
import es.deusto.ingenieria.sd.strava.client.controller.UserController;

public class ChallengeWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    Object result;
    String name;

    public ChallengeWindow(ChallengeController controller, UserController uc) { // Change constructor parameter
        // Obtain the methods of the controller using reflection
        List<Method> methods = Arrays.asList(ChallengeController.class.getMethods()); // Change here

        Vector<String> methodNames = new Vector<>();
        methods.forEach(method -> {
            if (method.getName().contains("Challenge")) {
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
            	ChallengeWindow.this.dispose();
            }
        });
        getContentPane().add(back);

        // Update the lister for the JList to use ChallengeController
        endpointsJList.addListSelectionListener(e -> {
            String selectedValue = endpointsJList.getSelectedValue();

            StyledDocument resultsDoc = endpointResults.getStyledDocument();

            if (selectedValue != null && e.getValueIsAdjusting()) {
            	try {
                	Thread esperaDatosThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
		                	System.out.println("1");
		                    try {
								resultsDoc.insertString(resultsDoc.getLength(), selectedValue + "\n", grayText);
							} catch (BadLocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                    Class<? extends ChallengeController> challengeControllerClass = controller.getClass();
		                    
		                    if (selectedValue.contains("create")) {
		                    	CreateChallenge c = new CreateChallenge();
		                        
		                        while (!c.dataProcessed()) {
		                            try {
		                                TimeUnit.SECONDS.sleep(2);
		                            } catch (InterruptedException e) {
		                                e.printStackTrace();
		                            }
		                        }
		                        System.out.println("4");
	                    		String name = c.getName();
	                    		String startDate = c.getStartDate();
	                    		String endDate = c.getEndDate();
	                    		String sport = c.getSport();
	                    	    float targetDistance = c.getTargetDistance();
	                    	    long targetTime = c.getTargetTime();
	                    	    System.out.println(name);
	                    	    System.out.println(startDate);
	                    	    System.out.println(endDate);
	                    	    System.out.println(sport);
	                    	    System.out.println(targetDistance);
	                    	    System.out.println(targetTime);     
		                    
		                        
		                    }
                    	    else if (selectedValue.contains("getChallenges")) {
                            	result = controller.getChallenges();
                            }
                    	   
                    	    else if (selectedValue.contains("acceptChallenge")) {
		                        acceptChallenge ac = new acceptChallenge();
		                        while (!ac.dataProcessed()) {
		                            try {
		                                TimeUnit.SECONDS.sleep(2);
		                            } catch (InterruptedException e) {
		                                e.printStackTrace();
		                            }
		                        }

		                        name = ac.getName();
		                        result = controller.acceptChallenge(uc.getToken()+"", selectedValue);   
		                    }
                            else if (selectedValue.contains("getAcceptedChallenges")) {
                            	result = controller.getAcceptedChallenges(uc.getToken()+"",Calendar.getInstance().getTimeInMillis()+"");
                            }
                            else if (selectedValue.contains("getActiveChallenges")) {
                            	result = controller.getActiveChallenges(uc.getToken()+"",Calendar.getInstance().getTimeInMillis()+"");
                            }
		                    
		                    // Getting method by name
		                    //Method method = userControllerClass.getMethod(selectedValue);
		                    System.out.println("4");
		                    // Method invocation using reflection
		                    //Object result = method.invoke(controller);
		                    
		                    
		                    //System.out.println(method.getName());
		                    
		                    if (result != null) {
		                        try {
									resultsDoc.insertString(resultsDoc.getLength(), result.toString() + "\n\n", greenText);
								} catch (BadLocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                    }
                        }
                	});
                	esperaDatosThread.start();
                

                } catch (Exception ex1) {
                    try {
                        resultsDoc.insertString(resultsDoc.getLength(),
                                " - The invocation throws an exception: " + ex1.getMessage() + "\n\n", orangeText);
                    } catch (Exception ex2) {
                    }
                }
                
            }
        });

        this.setTitle("SpringBoot Challenge Application GUI");
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(endpointsJList, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(1024, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
}
