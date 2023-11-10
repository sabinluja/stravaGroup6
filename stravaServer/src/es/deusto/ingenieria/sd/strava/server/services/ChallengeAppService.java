package es.deusto.ingenieria.sd.strava.server.services;

import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class ChallengeAppService {
	
	public ChallengeAppService() {}

	@SuppressWarnings("unused")
	private boolean createChallengue(User user, String name, String startDate, String endDate, float targetDistance, 
			long targetTime, String sport) {
		
		return true;
	}
	
	@SuppressWarnings("unused")
	private boolean acceptChallengue(User user, String challengeName) {
		
		return true;
	}
	
	@SuppressWarnings("unused")
	private List<Challenge> getActiveChallengues(User user, String date) {
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private List<Challenge> getAcceptedChallengues(User user) {

		return null;
	}
}
