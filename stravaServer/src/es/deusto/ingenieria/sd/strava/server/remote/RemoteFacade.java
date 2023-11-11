package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.strava.server.services.ChallengeAppService;
import es.deusto.ingenieria.sd.strava.server.services.SessionAppService;
import es.deusto.ingenieria.sd.strava.server.services.UserAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {
    private static final long serialVersionUID = 1L;
    
    private Map<Long, User> serverState = new HashMap<>();
    private ChallengeAppService challengeService = new ChallengeAppService();
    private UserAppService userService = new UserAppService();
    private SessionAppService sessionService = new SessionAppService();
    
    public RemoteFacade() throws RemoteException {super();}
    
	@SuppressWarnings("unlikely-arg-type")
	public boolean createChallenge(String token, String name, String startDate,
                                                 String endDate, float targetDistance,
                                                 long targetTime, String sport) throws RemoteException {
    	// Implementation of a challenge
    	
    	System.out.println(" * RemoteFacade createChallenge");
    	
		if (this.serverState.containsKey(token)) {						
			if (challengeService.createChallenge(getUserByToken(token), name, startDate, endDate,
			        targetDistance, targetTime, sport)) {
				return true;
			} else {
				throw new RemoteException("createChallenge() fails!");
			}
		} else {
			throw new RemoteException("To create a challenge you must first log in");
		}
    }
    
    public List<ChallengeDTO> getChallenges() throws RemoteException {
        // Implementation based on ChallengeAppService
           
        System.out.println(" * RemoteFacade getChallenges()");
        List<Challenge> challenges = challengeService.getChallenges();
		
		if (challenges != null) {
			//Convert domain object to DTO
	        return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}
    }

 
    public boolean acceptChallenge(String token, String challengeName) throws RemoteException {
        // Implementation based on ChallengeAppService
    			
		System.out.println(" * RemoteFacade acceptChallenge()");
        List<Challenge> challenges = challengeService.getChallenges();
		
		if (challenges != null) {
			//Convert domain object to DTO
			return challengeService.acceptChallenge(getUserByToken(token), challengeName);
		} else {
			throw new RemoteException("acceptChallenge() fails!");
		}
    }
    
    public List<ChallengeDTO> getActiveChallenges(String token, String date) throws RemoteException {
        // Implementation based on ChallengeAppService
    	
        System.out.println(" * RemoteFacade getActiveChallenges: \"'"  + date + "')");
        List<Challenge> activeChallenges = challengeService.getActiveChallenges(getUserByToken(token), date);
		
		if (activeChallenges != null) {
			//Convert domain object to DTO
	        return ChallengeAssembler.getInstance().challengeToDTO(activeChallenges);
		} else {
			throw new RemoteException("getActiveChallenges() fails!");
		}
    }
    
    public List<ChallengeDTO> getAcceptedChallenges(String token) throws RemoteException {
        // Implementation based on ChallengeAppService
        
        System.out.println(" * RemoteFacade getAcceptedChallenges: '"  + token + "')");
        List<Challenge> acceptedChallenges = challengeService.getAcceptedChallenges(getUserByToken(token));
		
		if (acceptedChallenges != null) {
			//Convert domain object to DTO
	        return ChallengeAssembler.getInstance().challengeToDTO(acceptedChallenges);
		} else {
			throw new RemoteException("getAcceptedChallenges() fails!");
		}
    }
    
    public boolean registerGoogle(String email, String name, String birthDate) {
        // Implementation based on UserAppService
    	
        return userService.registerGoogle(email, name, birthDate);
    }
    
    public boolean registerFacebook(String email, String name, String birthDate) {
        // Implementation based on UserAppService
    	
        return userService.registerFacebook(email, name, birthDate);
    }
    
    public boolean registerGoogle(String email, String name, String birthDate,
                                  float weight, int height, int maxHeartRate, int restHeartRate) {
        // Implementation based on UserAppService
    	
        return userService.registerGoogle(email, name, birthDate, weight, height, maxHeartRate, restHeartRate);
    }
    
    public boolean registerFacebook(String email, String name, String birthDate,
                                    float weight, int height, int maxHeartRate, int restHeartRate) {
        // Implementation based on UserAppService
    	
        return userService.registerFacebook(email, name, birthDate, weight, height, maxHeartRate, restHeartRate);
    }
    
    public boolean createSession(String token, String title, String sport, float distance,
                                              String startDate, long startTime, int duration) {
        // Implementation based on SessionAppService
    	
        try {
			return sessionService.createSession(getUserByToken(token), title, sport, distance, startDate, startTime, duration);
		} catch (RemoteException e) {e.printStackTrace();}
        
		return false;
    }
    
    public List<SessionDTO> getSessions(String token) throws RemoteException {
        // Implementation based on SessionAppService
        	        
        System.out.println(" * RemoteFacade getSessions('" + token + "')");
        List<Session> sessions = sessionService.getSessions(getUserByToken(token));
        
		if (sessions != null) {
			//Convert domain object to DTO
			return SessionAssembler.getInstance().sessionToDTO(sessions);
		} else {
			throw new RemoteException("getSessions() fails!");
		}
    }
    
    public synchronized long login(String email, String password) throws RemoteException {
        // Implementation based on LoginAppService
    	
    	System.out.println(" * RemoteFacade login(): " + email + " / " + password);
		User user = userService.login(email, password);
			
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);	
				return(token);
				
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
    }
    
    public synchronized void logout(String token) throws RemoteException {
        // Implementation of logout
    	
    	System.out.println(" * RemoteFacade logout(): " + token);
    	
        Long tokenId = Long.parseLong(token);
        if (serverState.containsKey(tokenId)) {
            serverState.remove(tokenId);
        } else {
            throw new RemoteException("User is not logged in!");
        }
    }
    
    public User getUserByToken(String token) throws RemoteException {
    	// Implementation of the token
    	
        Long tokenId = Long.parseLong(token);
        return serverState.get(tokenId);
    }
}