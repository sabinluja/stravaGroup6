package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;

public interface IRemoteFacade {

    boolean createChallenge(String token, String name, String startDate, String endDate, float targetDistance,
                            long targetTime, String sport);

    List<Challenge> getChallenges();
    boolean acceptChallenge(String token, String challengeName);
    List<Challenge> getActiveChallenges(String token, String date);
    List<Challenge> getAcceptedChallenges(String token);

    boolean registerGoogle(String email, String name, String birthDate);
    boolean registerFacebook(String email, String name, String birthDate);
    boolean registerGoogle(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate);
    boolean registerFacebook(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate);
    User login(String email, String password);

    boolean createSession(String token, String style, String sport, float distance, String startDate, long startTime, int duration);
    List<SessionDTO> getSessions(String token);
    void logout(String token) throws RemoteException;
}