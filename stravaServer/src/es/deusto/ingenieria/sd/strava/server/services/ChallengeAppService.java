package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class ChallengeAppService {
	
	private List<Challenge> challengeDTOList = new ArrayList<>();
	
	public ChallengeAppService() {}

	public boolean createChallenge(User user, String name, String startDate, String endDate, float targetDistance, 
			long targetTime, String sport) {
        Challenge newChallenge = new Challenge(name, startDate, endDate, targetTime, targetDistance, sport);

		if (user != null) {
            user.addChallenge(newChallenge);
            return true; // The challenge has been correctly created
        }
        return false; // The challenge has not been created
	}
	
	public List<Challenge> getChallenges() {
		return this.challengeDTOList;
	}
	
	public boolean acceptChallenge(User user, String challengeName) {
		if (user != null) {
            for (Challenge challenge : user.getChallengeList()) {
                if (challenge.getName().equals(challengeName)) {
                    return true; // Challenge found and accepted
                }
            }
        }
        return false; // Challenge not found or unable to accept
	}
	
	public List<Challenge> getActiveChallenges(User user, String date) {
		List<Challenge> activeChallengesList = new ArrayList<>();

        if (user != null) {
            for (Challenge challenge : user.getChallengeList()) {
                if (date.compareTo(challenge.getStartDate()) >= 0 && date.compareTo(challenge.getEndDate()) <= 0) {
                	activeChallengesList.add(challenge);
                }
                
            }
        }
        return activeChallengesList; // The list with the active challenges of that concrete user
	}
	
	public List<Challenge> getAcceptedChallenges(User user) {
		List<Challenge> acceptedChallengesList = new ArrayList<>();

        if (user != null) {
            for (Challenge challenge : user.getChallengeList()) {
            	if (acceptChallenge(user, challenge.getName()))
            		acceptedChallengesList.add(challenge); 
            }
        }
        return acceptedChallengesList; // The list with the accepted challenges of that concrete user
	}
}
