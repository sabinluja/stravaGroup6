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
    String provider;
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
    JButton backButton;
    JButton registerButton;
    JPanel cards;
    private JLabel label;

    public UserWindow(UserController controller, ChallengeController chc, SessionController sc) {
    this.setTitle("SpringBoot Client Application GUI");
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
                 //handleLogin(controller);
            	 handleCard("card2");
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
         
         
         logoutButton = new JButton("Logout"); // Cambiado el texto a "Logout"
         logoutButton.setBackground(Color.WHITE);
         logoutButton.setBounds(84, 114, 100, 23); // Cambiado el valor de Y para que no se superponga con el botón Register
         logoutButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 handleLogout(controller);
             }
         });
         card1.add(logoutButton); // Agregado el botón de logout al panel card1

         
         getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
         card1.add(registerButton);

         // Card 2: Challenge and Session buttons
         JPanel card2 = new JPanel();
         card2.setLayout(null);

         challenge = new JButton("Challenge");
         challenge.setBackground(Color.WHITE);
         challenge.setBounds(84, 46, 100, 23);
         card2.add(challenge);
         
         backButton = new JButton("Back");
         backButton.setBackground(Color.WHITE);
         backButton.setBounds(84, 114, 100, 23);
         backButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 handleCard("card1");
             }
         });
         card2.add(backButton);

         session = new JButton("Session");
         session.setBackground(Color.WHITE);
         session.setBounds(84, 80, 100, 23);
         card2.add(session);
         cards.setLayout(new CardLayout(0, 0));

         // Add cards to the CardLayout
         cards.add(card1, "card1");
         
         label = new JLabel("");
         cards.add(label, "name_877759002109600");
         cards.add(card2, "card2");

         getContentPane().add(cards);

         this.setSize(300, 200);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }


    private void handleCard(String card) {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, card);

        if (card.equals("card12")) { // Si es la tarjeta con los botones de login y registro
            // Habilitar o deshabilitar botones según sea necesario
            logoutButton.setEnabled(false);
            challenge.setEnabled(false);
            session.setEnabled(false);
            loginButton.setEnabled(true);
            registerButton.setEnabled(true);
        } else if (card.equals("card21")) { // Si es la tarjeta con los botones de Challenge y Session
            // Habilitar o deshabilitar botones según sea necesario
            backButton.setEnabled(true);
            challenge.setEnabled(true);
            session.setEnabled(true);
            loginButton.setEnabled(false);
            registerButton.setEnabled(false);
        }
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
	        
	        
	        
	        provider=r.getEmail();//CAMBIAR A PROVIDER
	        password=r.getEmail();//CAMBIAR A CONTRASEÑA
	        email = r.getEmail();
	        nombre = r.getNombre();
	        birthDate = r.getBirthDate();
	        weight = r.getWeight();
	        height = r.getHeight();
	        maxHeart = r.getMaxHeart();
	        restHeart = r.getRestHeart();
	        result = controller.register(email, nombre, birthDate,password,provider, weight, height, maxHeart, restHeart);
	        
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

        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, "Card1");

        challenge.setEnabled(false);
        session.setEnabled(false);
        logoutButton.setEnabled(false);


        loginButton.setEnabled(true);
        registerButton.setEnabled(true);
    }
}
