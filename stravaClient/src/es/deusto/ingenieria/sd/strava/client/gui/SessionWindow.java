package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import es.deusto.ingenieria.sd.strava.client.controller.SessionController;
import es.deusto.ingenieria.sd.strava.client.controller.UserController;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;

public class SessionWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    Object result;
    String token;
    String title;
    String sport;
    float distance;
	String startDate;
	long startTime;
	int duration;

    public SessionWindow(SessionController controller, UserController uc) { // Change constructor parameter
        // Obtain the methods of the controller using reflection
        List<Method> methods = Arrays.asList(SessionController.class.getMethods()); // Change here
      
        
        Vector<String> methodNames = new Vector<>();
        methods.forEach(method -> {
            if (method.getName().contains("Session")) {
                methodNames.add(method.getName());
            }
        });

        Collections.sort(methodNames);
        
        JButton createSession = new JButton("Create Session");
        createSession.setBackground(Color.WHITE);
        createSession.setBounds(75, 10, 130, 23);
        
        createSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Thread createSessionT = new Thread(() -> {
	            	CreateSession s = new CreateSession();
	                
	                while (!s.dataProcessed()) {
	                    try {
	                        TimeUnit.SECONDS.sleep(2);
	                    } catch (InterruptedException e1) {
	                        e1.printStackTrace();
	                    }
	                }
	                
	                token = uc.getToken()+"";
	                title = s.getTitle();
	                sport = s.getSport();
	                distance = s.getDistance();
	            	startDate = s.getStartDate();
	            	startTime = s.getStartTime();
	            	duration = s.getDuration();
	                
	                result = controller.createSession(token, title, sport, distance, startDate, startTime, duration);
	                System.out.println(result);
	                
	                if (result.toString().equals("true")) {
	        	    	JOptionPane.showMessageDialog(null, "Session successfully created", "Session", JOptionPane.INFORMATION_MESSAGE);
	        	    } else {
	        	    	JOptionPane.showMessageDialog(null, "Session has not been created", "Session", JOptionPane.INFORMATION_MESSAGE);
	        	    }
	                
            	}); createSessionT.start();
            }
        });

        JButton getSessions = new JButton("Get Sessions");
        getSessions.setBackground(Color.WHITE);
        getSessions.setBounds(75, 40, 130, 23);
        
        getSessions.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
                try {
                    token = uc.getToken()+"";
                    result = controller.getSessions(token);

                    showSessionsDialog((List<SessionDTO>) result);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        });
        
        JButton back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setBounds(10, 80, 89, 23);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SessionWindow.this.dispose();
            }
        });
        
        getContentPane().setLayout(null);
        getContentPane().add(createSession);
        getContentPane().add(getSessions);
        getContentPane().add(back);

        this.setTitle("Session");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    private void showSessionsDialog(List<SessionDTO> sessions) {
        JDialog dialog = new JDialog(this, "Session List", true);
        dialog.setSize(800, 150);

        JTextArea sessionsTextArea = new JTextArea();
        sessionsTextArea.setEditable(false);

        for (SessionDTO session : sessions) {
            sessionsTextArea.append(session.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(sessionsTextArea);
        dialog.add(scrollPane);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
