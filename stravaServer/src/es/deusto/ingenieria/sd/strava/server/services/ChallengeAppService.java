package es.deusto.ingenieria.sd.strava.server.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class ChallengeAppService {
	
	private List<Challenge> challengeList = new ArrayList<>();
	
	public ChallengeAppService() {}

	public boolean createChallenge(User user, String name, String startDate, String endDate, float targetDistance, 
			long targetTime, String sport) {
        Challenge newChallenge = new Challenge(name, startDate, endDate, targetTime, targetDistance, sport);

		if (user != null) {
            user.addChallenge(newChallenge);
            challengeList.add(newChallenge);

            return true; // The challenge has been correctly created
        }
        return false; // The challenge has not been created
	}
	
	public List<Challenge> getChallenges() {
		return this.challengeList;
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date currentDate = dateFormat.parse(date);

                for (Challenge challenge : user.getChallengeList()) {
                    Date startDate = dateFormat.parse(challenge.getStartDate());
                    Date endDate = dateFormat.parse(challenge.getEndDate());

                    if (currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0) {
                        activeChallengesList.add(challenge);
                    }
                }
            } catch (ParseException e) {
                // Handle the ParseException according to your requirements
                e.printStackTrace();
            }
        }

        return activeChallengesList;
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
