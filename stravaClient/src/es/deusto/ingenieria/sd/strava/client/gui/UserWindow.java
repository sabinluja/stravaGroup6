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
    String birthDate, password, nombre, email, provider;
    float weight;
    int maxHeart, restHeart, height;
    JButton registerButton, backButton, logoutButton, loginButton, session, challenge;
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
         loginButton.setBounds(85, 20, 100, 23);
         loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 handleLogin(controller);
             }
         });
         card1.add(loginButton);

         registerButton = new JButton("Register");
         registerButton.setBackground(Color.WHITE);
         registerButton.setBounds(85, 50, 100, 23);
         registerButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 handleRegister(controller);
             }
         });
    
         logoutButton = new JButton("Logout");
         logoutButton.setBackground(Color.WHITE);
         logoutButton.setBounds(85, 80, 100, 23);
         logoutButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 handleLogout(controller);
             }
         });
         card1.add(logoutButton);
         
         getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
         card1.add(registerButton);

         // Card 2: Challenge and Session buttons
         JPanel card2 = new JPanel();
         card2.setLayout(null);

         challenge = new JButton("Challenge");
         challenge.setBackground(Color.WHITE);
         challenge.setBounds(85, 20, 100, 23);
         
         challenge.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 ChallengeWindow cw = new ChallengeWindow(chc, controller);
            	 cw.setVisible(true);
             }
         });
         
         card2.add(challenge);

         
         backButton = new JButton("Back");
         backButton.setBackground(Color.WHITE);
         backButton.setBounds(85, 80, 100, 23);
         backButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 handleCard("card1");
             }
         });
         card2.add(backButton);

         session = new JButton("Session");
         session.setBackground(Color.WHITE);
         session.setBounds(85, 50, 100, 23);
         
         session.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 SessionWindow sw = new SessionWindow(sc, controller);
            	 sw.setVisible(true);
             }
         });
         
         card2.add(session);
         cards.setLayout(new CardLayout(0, 0));

         cards.add(card1, "card1");
         
         label = new JLabel("");
         cards.add(label, "name_877759002109600");
         cards.add(card2, "card2");

         getContentPane().add(cards);

         this.setSize(300, 160);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }

    private void handleCard(String card) {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, card);
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

	        provider = r.getProvider();
	        password = r.getPassword();
	        email = r.getEmail();
	        nombre = r.getNombre();
	        birthDate = r.getBirthDate();
	        weight = r.getWeight();
	        height = r.getHeight();
	        maxHeart = r.getMaxHeart();
	        restHeart = r.getRestHeart();
	        result = controller.register(email, nombre, birthDate,password,provider, weight, height, maxHeart, restHeart);

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
				handleCard("card2");
			}
	    }); waitDataLogin.start();
    }
    
    private void handleLogout(UserController controller) {
        controller.logout();
    }
}