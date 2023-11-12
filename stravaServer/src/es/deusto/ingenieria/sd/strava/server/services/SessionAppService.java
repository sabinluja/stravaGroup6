package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class SessionAppService {
	
	public SessionAppService() {}
	
	public boolean createSession(User user, String title, String sport, float distance, String startDate, long startTime, int duration) {
		
		System.out.println("appservice");
		if (user != null) {
			System.out.println("entra");
			Session newSession = new Session();
			newSession.setTitle(title);
			newSession.setSports(sport);
			newSession.setDistance(distance);
			newSession.setStartDate(startDate);
			newSession.setStartTime(startTime);
			newSession.setDuration(duration);
            user.addSession(newSession);
            return true; // The challenge has been correctly created
        }
        return false; // The challenge has not been created
	}
	
	public List<Session> getSessions(User user) {
		List<Session> userSessions = new ArrayList<>();
		
		if (user != null) {
			userSessions = user.getSessionList();
		}
		return userSessions; // All the sessions of that user
	}
}
