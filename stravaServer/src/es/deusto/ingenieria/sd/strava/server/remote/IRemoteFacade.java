package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;

public interface IRemoteFacade {

    boolean createChallenge(String token, String name, String startDate, String endDate, float targetDistance,
                            long targetTime, String sport)throws RemoteException;

    List<ChallengeDTO> getChallenges()throws RemoteException;

    boolean acceptChallenge(String token, String challengeName)throws RemoteException;

    List<ChallengeDTO> getActiveChallenges(String token, String date) throws RemoteException;

    List<ChallengeDTO> getAcceptedChallenges(String token)throws RemoteException;

    boolean registerGoogle(String email, String name, String birthDate)throws RemoteException;

    boolean registerFacebook(String email, String name, String birthDate)throws RemoteException;

    boolean registerGoogle(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate)throws RemoteException;

    boolean registerFacebook(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate)throws RemoteException;

    User login(String email, String password)throws RemoteException;

    boolean createSession(String token, String style, String sport, float distance, String startDate, long startTime, int duration)throws RemoteException;

    List<SessionDTO> getSessions(String token)throws RemoteException;

    void logout(String token) throws RemoteException; 
}

