package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class ChallengeController {
	
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	
	public ChallengeController (ServiceLocator serviceLoc) {
		this.serviceLocator = serviceLoc;
	}
	
	public boolean createChallenge(String token, String name, String startDate, String endDate, float targetDistance,
				long targetTime, String sport) {
		try {
			return this.serviceLocator.getService().createChallenge(token, name, startDate, endDate, targetDistance, targetTime, sport);
		} catch (RemoteException e) {
			System.out.println("# Error creating the challenge: " + e);
			return false;
		}
	}
	
	public List<ChallengeDTO> getChallenges() {
		try {
			return this.serviceLocator.getService().getChallenges();
		} catch (RemoteException e) {
			System.out.println("# Error getting all challenges: " + e);
			return null;
		}
	}
	
	public boolean acceptChallenge(String token, String challengeName) {
		try {
			return this.serviceLocator.getService().acceptChallenge(token, challengeName);
		} catch (RemoteException e) {
			System.out.println("# Error accepting the challenge: " + e);
			return false;
		}
	}
	
	public List<ChallengeDTO> getActiveChallenges(String token, String date) {
		try {
			return this.serviceLocator.getService().getActiveChallenges(token, date);
		} catch (RemoteException e) {
			System.out.println("# Error getting the active challenges: " + e);
			return null;
		}
	}
	
	public List<ChallengeDTO> getAcceptedChallenges(String token, String date) {
		try {
			return this.serviceLocator.getService().getAcceptedChallenges(token, date);
		} catch (RemoteException e) {
			System.out.println("# Error getting the accepted challenges: " + e);
			return null;
		}
	}
}
