package es.deusto.ingenieria.sd.strava.client.gui;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import es.deusto.ingenieria.sd.strava.client.gui.Login;
import es.deusto.ingenieria.sd.strava.client.gui.Register;


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
    JButton challenge;
    JButton session;
    JButton loginButton;
    JButton logoutButton;
    JButton registerButton;
    JPanel cards;
    private JLabel label;

    public UserWindow(UserController controller, ChallengeController chc, SessionController sc) {
    this.setTitle("Strava");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // Create cards panel with CardLayout
         cards = new JPanel();

         // Card 1: Login and Register buttons
         JPanel card1 = new JPanel();
         card1.setLayout(null);

         loginButton = new JButton("Login");
         loginButton.setBackground(Color.WHITE);
         loginButton.setBounds(84, 46, 100, 23);
         loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 handleLogin(controller);
             }
         });
         card1.add(loginButton);

         registerButton = new JButton("Register");
         registerButton.setBackground(Color.WHITE);
         registerButton.setBounds(84, 80, 100, 23);
         registerButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 handleRegister(controller);
             }
         });
         
         
         logoutButton = new JButton("logout");
         logoutButton.setBackground(Color.WHITE);
         logoutButton.setBounds(84, 80, 100, 23);
         logoutButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 handleLogout(controller);
             }
         });
         
         
         
         getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
         card1.add(registerButton);

         // Card 2: Challenge and Session buttons
         JPanel card2 = new JPanel();
         card2.setLayout(null);

         challenge = new JButton("Challenge");
         challenge.setBackground(Color.WHITE);
         challenge.setBounds(84, 46, 100, 23);
         challenge.setEnabled(false);
         card2.add(challenge);

         session = new JButton("Session");
         session.setBackground(Color.WHITE);
         session.setBounds(84, 80, 100, 23);
         session.setEnabled(false);
         card2.add(session);
         cards.setLayout(new CardLayout(0, 0));

         // Add cards to the CardLayout
         cards.add(card1, "name_877758968441800");
         
         label = new JLabel("");
         cards.add(label, "name_877759002109600");
         cards.add(card2, "name_877759035848900");

         getContentPane().add(cards);

         this.setSize(300, 200);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }


    private void handleButtonPress(String buttonText) {
        // Handle button press based on the buttonText
        System.out.println("Button pressed: " + buttonText);
    }

    private void handleRegister(UserController controller) {
    	Thread waitDataRegister = new Thread(() -> {
	        Register r = new Register();
	        while (!r.datosProcesados()) {
	            try {
	                TimeUnit.SECONDS.sleep(2);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        email = r.getEmail();
	        nombre = r.getNombre();
	        birthDate = r.getBirthDate();
	        weight = r.getWeight();
	        height = r.getHeight();
	        maxHeart = r.getMaxHeart();
	        restHeart = r.getRestHeart();
	        result = controller.registerGoogle(email, nombre, birthDate, weight, height, maxHeart, restHeart);
	        
	        if (result != null && result.equals(true)) {
	            // Successful registration, switch to the second card
	            CardLayout cardLayout = (CardLayout) cards.getLayout();
	            cardLayout.show(cards, "Card2");
	
	            // Enable Challenge and Session buttons
	            challenge.setEnabled(true);
	            session.setEnabled(true);
	
	            // Disable Login and Register buttons
	            loginButton.setEnabled(false);
	            registerButton.setEnabled(false);
	
	            // Enable Logout button
	            logoutButton.setEnabled(true);
	        } else {
	            // Registration failed, keep the buttons in their current state
	            challenge.setEnabled(false);
	            session.setEnabled(false);
	        }
    	}); waitDataRegister.start();
    }

    private void handleLogin(UserController controller) {
	    Thread waitDataLogin = new Thread(() -> {
		    Login l = new Login();
		    
		    while (!l.dataProcessed()) {
		    	try {
		    		TimeUnit.SECONDS.sleep(2);
		    	} catch (InterruptedException e) {e.printStackTrace();}
		    }
	
			email = l.getUsername();
			password = l.getPassword();
			result = controller.login(email, password);
			
			if (result != null && result.equals(true)) {
			   // Successful login, switch to the second card
			   CardLayout cardLayout = (CardLayout) cards.getLayout();
			   cardLayout.show(cards, "Card2");
			
			   // Enable Challenge and Session buttons
			   challenge.setEnabled(true);
			   session.setEnabled(true);
			
			   // Disable Login and Register buttons
			   loginButton.setEnabled(false);
			   registerButton.setEnabled(false);
			
			   // Enable Logout button
			   logoutButton.setEnabled(true);
			} else {
			   // Login failed, disable Challenge and Session buttons
			   challenge.setEnabled(false);
			   session.setEnabled(false);
			}
	    }); waitDataLogin.start();
    }
    
    
    private void handleLogout(UserController controller) {
        controller.logout();
        // Handle the logout process
        // ...

        // Switch back to the first card
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, "Card1");

        // Disable Challenge, Session, and Logout buttons
        challenge.setEnabled(false);
        session.setEnabled(false);
        logoutButton.setEnabled(false);

        // Enable Login and Register buttons
        loginButton.setEnabled(true);
        registerButton.setEnabled(true);
    }
}
