package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class SessionController {
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;

	public SessionController (ServiceLocator serviceLoc) {
		this.serviceLocator = serviceLoc;
	}
	
	public boolean createSession(String token, String title, String sport, float distance,
            	String startDate, long startTime, int duration) {
		try {
			System.out.println("controller");
			return this.serviceLocator.getService().createSession(token, title, sport, distance, startDate, startTime, duration);
		} catch (RemoteException e) {
			System.out.println("# Error creating the session: " + e);
			return false;
		}
	}
	
	public List<SessionDTO> getSessions(String token) throws RemoteException {
		try {
			//List<SessionDTO> ret = this.serviceLocator.getService().getSessions(token);
			//System.out.println(ret);
			return this.serviceLocator.getService().getSessions(token);
		} catch (RemoteException e) {
			System.out.println("# Error getting all sessions: " + e);
			return null;
		}
    }
}
