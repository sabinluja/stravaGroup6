package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class SessionAppService {
	
	public SessionAppService() {
		initilizeData();
	}
	
	private void initilizeData() {
		Session s1 = new Session();
		s1.setTitle("Hola");
		s1.setSports("Running");
		s1.setDistance(10);
        s1.setStartDate("hoy");
    	s1.setStartTime(100);
    	s1.setDuration(50);
    	
    	Session s2 = new Session();
		s2.setTitle("Adios");
		s2.setSports("Running");
		s2.setDistance(100);
        s2.setStartDate("ayer");
    	s2.setStartTime(200);
    	s2.setDuration(60);
    	
    	//userSessions.add(s1);
    	//userSessions.add(s2);
	}
	
	public boolean createSession(User user, String title, String sport, float distance, String startDate, long startTime, int duration) {
		
		System.out.println("appservice");
		if (user != null) {
			Session newSession = new Session();
			newSession.setTitle(title);
			newSession.setSports(sport);
			newSession.setDistance(distance);
			newSession.setStartDate(startDate);
			newSession.setStartTime(startTime);
			newSession.setDuration(duration);
            user.addSession(newSession);
            System.out.println(user.toString());
            return true; // The challenge has been correctly created
        }
        return false; // The challenge has not been created
	}
	
	public List<Session> getSessions(User user) {
		List<Session> userSessions = new ArrayList<>();
		if (user != null) {
			for (Session s : user.getSessionList()) {
				userSessions.add(s);
			}
		}
		return userSessions; // All the sessions of that user
	}
}
