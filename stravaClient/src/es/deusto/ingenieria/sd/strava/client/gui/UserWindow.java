package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Arrays;
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
import es.deusto.ingenieria.sd.strava.client.controller.SessionController;
import es.deusto.ingenieria.sd.strava.client.controller.UserController;

public class UserWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    Object result;
    String email;
    String nombre;
    String birthDate;
    String password;
    float weight;
    int height;
    int maxHeart;
    int restHeart;

    public UserWindow(UserController controller, ChallengeController chc, SessionController sc) { // Change constructor parameter
        // Obtain the methods of the controller using reflection
        List<Method> methods = Arrays.asList(UserController.class.getMethods()); // Change here
        
        Vector<String> methodNames = new Vector<>();
        methods.forEach(method -> {
            if (method.getName().contains("register")) {
                methodNames.add(method.getName());
            }
            if (method.getName().contains("login")) {
                methodNames.add(method.getName());
            }
        });

        Collections.sort(methodNames);

        JList<String> endpointsJList = new JList<>(methodNames);
        endpointsJList.setBounds(-5, 0, 200, 561);
        endpointsJList.setBorder(new TitledBorder("Endpoints"));
        endpointsJList.setSelectedIndex(-1);
        endpointsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        endpointsJList.setPreferredSize(new Dimension(200, 200));

        JTextPane endpointResults = new JTextPane();
        endpointResults.setBackground(new Color(0, 40, 51));
        endpointResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(endpointResults);
        scrollPane.setBounds(205, 0, 803, 561);
        scrollPane.setBorder(new TitledBorder("Server responses"));

        SimpleAttributeSet orangeText = new SimpleAttributeSet();
        StyleConstants.setForeground(orangeText, Color.ORANGE);

        SimpleAttributeSet greenText = new SimpleAttributeSet();
        StyleConstants.setForeground(greenText, new Color(133, 153, 1));

        SimpleAttributeSet grayText = new SimpleAttributeSet();
        StyleConstants.setForeground(grayText, new Color(131, 148, 149));
        StyleConstants.setBold(grayText, true);

        // Update the lister for the JList to use UserController
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
		                    Class<? extends UserController> userControllerClass = controller.getClass();
		                    
		                    if (selectedValue.contains("register")) {
		                        Register r = new Register();
		                        
		                        while (!r.datosProcesados()) {
		                            try {
		                                TimeUnit.SECONDS.sleep(2);
		                            } catch (InterruptedException e) {
		                                e.printStackTrace();
		                            }
		                        }
		                        System.out.println("pene1");
		                        email = r.getEmail();
		                        nombre = r.getNombre();
		                        birthDate = r.getBirthDate();
		                        weight = r.getWeight();
		                        height = r.getHeight();
		                        maxHeart = r.getMaxHeart();
		                        restHeart = r.getRestHeart();
		                        result = controller.registerGoogle(email, nombre, birthDate, weight, height, maxHeart, restHeart);     
		                    }
		                        
		                    
		                    if (selectedValue.contains("login")) {
		                        Login l = new Login();
		                        while (!l.dataProcessed()) {
		                            try {
		                                TimeUnit.SECONDS.sleep(2);
		                            } catch (InterruptedException e) {
		                                e.printStackTrace();
		                            }
		                        }

		                        email = l.getUsername();
		                        password = l.getPassword();  
		                        result = controller.login(email, password);     
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

        this.setTitle("SpringBoot Client Application GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        

        JButton challenge = new JButton("Challenge");
        challenge.setBackground(Color.WHITE);
        challenge.setBounds(10, 470, 100, 23);
        challenge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChallengeWindow challengeWindow = new ChallengeWindow(chc);
                challengeWindow.setVisible(true);
            }
        });
        getContentPane().add(challenge);

        JButton session = new JButton("Session");
        session.setBackground(Color.WHITE);
        session.setBounds(10, 504, 100, 23);
        session.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SessionWindow sessionWindow = new SessionWindow(sc); // Cambia esto por la instancia real
                sessionWindow.setVisible(true);
            }
        });
        getContentPane().add(session);
        
        getContentPane().add(session);
        getContentPane().add(endpointsJList);
        getContentPane().add(scrollPane);

        this.setSize(1024, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
