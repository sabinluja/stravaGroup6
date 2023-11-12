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
	
	public ChallengeAppService() {
		initialize();
	}
	
	public void initialize() {
		// CHALLENGE 
        Challenge challenge1 = new Challenge();
        challenge1.setName("Challenge 1");
        challenge1.setStartDate("2023-01-01");
        challenge1.setEndDate("2023-01-10");
        challenge1.setTargetTime(3600); // 1 hour
        challenge1.setTargetDistance(10.0f); // 10 kilometers
        challenge1.setSports("Running");

        Challenge challenge2 = new Challenge();
        challenge2.setName("Challenge 2");
        challenge2.setStartDate("2023-02-01");
        challenge2.setEndDate("2023-02-15");
        challenge2.setTargetTime(1800); // 30 minutes
        challenge2.setTargetDistance(5.0f); // 5 kilometers
        challenge2.setSports("Cycling");
	
        Challenge challenge3 = new Challenge();
        challenge3.setName("Challenge 3");
        challenge3.setStartDate("2023-03-01");
        challenge3.setEndDate("2023-03-15");
        challenge3.setTargetTime(5400); // 1.5 hours
        challenge3.setTargetDistance(3.0f); // 3 kilometers
        challenge3.setSports("Cycling");

        Challenge challenge4 = new Challenge();
        challenge4.setName("Challenge 4");
        challenge4.setStartDate("2023-04-01");
        challenge4.setEndDate("2023-04-20");
        challenge4.setTargetTime(7200); // 2 hours
        challenge4.setTargetDistance(8.0f); // 8 kilometers
        challenge4.setSports("Both");

        Challenge challenge5 = new Challenge();
        challenge5.setName("Challenge 5");
        challenge5.setStartDate("2023-05-01");
        challenge5.setEndDate("2023-05-30");
        challenge5.setTargetTime(10800); // 3 hours
        challenge5.setTargetDistance(20.0f); // 20 kilometers
        challenge5.setSports("Cycling");
        
        challengeList.add(challenge1);
        challengeList.add(challenge2);
        challengeList.add(challenge3);
        challengeList.add(challenge4);
        challengeList.add(challenge5);
	}

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
	        try {
	            // Parse the date from milliseconds as a String
	            Date currentDate = new Date(Long.parseLong(date));

	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	            for (Challenge challenge : user.getChallengeList()) {
	                // Parse start date and end date in "yyyy-MM-dd" format
	                Date startDate = dateFormat.parse(challenge.getStartDate());
	                Date endDate = dateFormat.parse(challenge.getEndDate());

	                if (currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0) {
	                    activeChallengesList.add(challenge);
	                }
	            }
	        } catch (NumberFormatException | ParseException e) {
	            // Handle the NumberFormatException or ParseException according to your requirements
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
