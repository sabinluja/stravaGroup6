package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;
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
        getContentPane().setLayout(null);

        
        JButton back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setBounds(10, 211, 89, 23);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ChallengeWindow.this.dispose();
            }
        });
        
        getContentPane().add(back);
        
        JButton create = new JButton("Creatre");
        create.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        create.setBounds(60, 10, 180, 30);
        create.setBackground(Color.WHITE);
        
        getContentPane().add(create);
        
        JButton getChallenges = new JButton("Get Challenges");
        getChallenges.setBounds(60, 50, 180, 30);
        getChallenges.setBackground(Color.WHITE);
        
        getContentPane().add(getChallenges);

        JButton acceptChallenge = new JButton("Accept Challenge");
        acceptChallenge.setBounds(60, 90, 180, 30);
        acceptChallenge.setBackground(Color.WHITE);
        
        getContentPane().add(acceptChallenge);
        
        JButton getAcceptedChallenges = new JButton("Get Accepted Challenge");
        getAcceptedChallenges.setBounds(60, 130, 180, 30);
        getAcceptedChallenges.setBackground(Color.WHITE);
        
        getContentPane().add(getAcceptedChallenges);
        
        JButton getActiveChallenges = new JButton("Get Active Challenge");
        getActiveChallenges.setBounds(60, 170, 180, 30);
        getActiveChallenges.setBackground(Color.WHITE);
        
        getContentPane().add(getActiveChallenges);

    	create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Thread createT = new Thread(() -> {
	            	CreateChallenge c = new CreateChallenge();
	                
	                while (!c.dataProcessed()) {
	                    try {
	                        TimeUnit.SECONDS.sleep(2);
	                    } catch (InterruptedException e1) {
	                        e1.printStackTrace();
	                    }
	                }
	                
	        		String name = c.getName();
	        		String startDate = c.getStartDate();
	        		String endDate = c.getEndDate();
	        		String sport = c.getSport();
	        	    float targetDistance = c.getTargetDistance();
	        	    long targetTime = c.getTargetTime();
	        	    result = controller.createChallenge(uc.getToken()+"", name, startDate, endDate, targetDistance, targetTime, sport);
	        	    System.out.println(result);
        	    
            	}); createT.start();
            }
        });
    	
    	getChallenges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
            	result = controller.getChallenges(); 
            	System.out.println(result);
            }
        });
	   
        acceptChallenge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e3) {
            	Thread acceptChallengeT = new Thread(() -> {
	            	acceptChallenge ac = new acceptChallenge();
	                while (!ac.dataProcessed()) {
	                    try {
	                        TimeUnit.SECONDS.sleep(2);
	                    } catch (InterruptedException e7) {
	                        e7.printStackTrace();
	                    }
	                }
	
	                name = ac.getName();
	                result = controller.acceptChallenge(uc.getToken()+"", name);
	                System.out.println(result);
                
            	}); acceptChallengeT.start();
            }
        });
        
        getAcceptedChallenges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e4) {
            	result = controller.getAcceptedChallenges(uc.getToken()+"",Calendar.getInstance().getTimeInMillis()+"");
            	System.out.println(result);
            }
        });
        
        getActiveChallenges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e5) {
            	result = controller.getActiveChallenges(uc.getToken()+"",Calendar.getInstance().getTimeInMillis()+"");
            	System.out.println(result);
            }
        });
        	
        this.setTitle("Challenge");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(300, 280);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
}
