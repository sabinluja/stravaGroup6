package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.SessionDAO;

public class SessionAppService {
	
	private SessionDAO sessionDAO = SessionDAO.getInstance();
	
	public SessionAppService() {}

	
	public boolean createSession(User user, String title, String sport, float distance, String startDate, long startTime, int duration) {
		
		if (user != null) {
			Session newSession = new Session();
			newSession.setTitle(title);
			newSession.setSports(sport);
			newSession.setDistance(distance);
			newSession.setStartDate(startDate);
			newSession.setStartTime(startTime);
			newSession.setDuration(duration);
            user.addSession(newSession);
            sessionDAO.store(newSession);
            return true; // The session has been correctly created
        }
        return false; // The session has not been created
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
