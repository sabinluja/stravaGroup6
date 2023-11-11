package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

    public RemoteFacade() throws RemoteException {
        super();
    }

    public boolean createChallenge(String token, String name, String startDate,
                                                 String endDate, float targetDistance,
                                                 long targetTime, String sport) {
        try {
			return challengeService.createChallenge(getUserByToken(token), name, startDate, endDate,
			        targetDistance, targetTime, sport);
		} catch (RemoteException e) {e.printStackTrace();}
        
		return false;
    }

    public List<ChallengeDTO> getChallenges() throws RemoteException {
        // Implementation based on ChallengeAppService
        List<Challenge> challenges = challengeService.getChallenges();
        return ChallengeAssembler.getInstance().challengeToDTO(challenges);
    }

    public boolean acceptChallenge(String token, String challengeName) {
        // Implementation based on ChallengeAppService
        try {
			return challengeService.acceptChallenge(getUserByToken(token), challengeName);
		} catch (RemoteException e) {e.printStackTrace();}
        
		return false;
    }

    public List<ChallengeDTO> getActiveChallenges(String token, String date) throws RemoteException {
        // Implementation based on ChallengeAppService
        List<Challenge> activeChallenges = challengeService.getActiveChallenges(getUserByToken(token), date);
        return ChallengeAssembler.getInstance().challengeToDTO(activeChallenges);
    }

    public List<ChallengeDTO> getAcceptedChallenges(String token) throws RemoteException {
        // Implementation based on ChallengeAppService
        List<Challenge> acceptedChallenges = challengeService.getAcceptedChallenges(getUserByToken(token));
        return ChallengeAssembler.getInstance().challengeToDTO(acceptedChallenges);
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

    public User login(String email, String password) {
        // Implementation based on LoginAppService
        return userService.login(email, password);
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
        List<Session> sessions = sessionService.getSessions(getUserByToken(token));
        return SessionAssembler.getInstance().sessionToDTO(sessions);
    }

    public void logout(String token) throws RemoteException {
        // Implementation of logout
        Long tokenId = Long.parseLong(token);
        if (serverState.containsKey(tokenId)) {
            serverState.remove(tokenId);
        } else {
            throw new RemoteException("User is not logged in!");
        }
    }

    public User getUserByToken(String token) throws RemoteException {
        Long tokenId = Long.parseLong(token);
        return serverState.get(tokenId);
    }
}