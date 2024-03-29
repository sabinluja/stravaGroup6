package es.deusto.ingenieria.sd.strava.server.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateways.MailSender;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.UserDAO;

public class ChallengeAppService {
		
	public ChallengeAppService() {
	}


	public boolean createChallenge(User user, String name, String startDate, String endDate, float targetDistance, 
			long targetTime, String sport) {
        Challenge newChallenge = new Challenge(name, startDate, endDate, targetTime, targetDistance, sport);

		if (user != null) {
            user.addChallenge(newChallenge);
            ChallengeDAO.getInstance().store(newChallenge);
            new MailSender(user.getEmail()).sendMessage("Challenge with the name "+newChallenge.getName()+" was created.");

            return true; // The challenge has been correctly created
        }
        return false; // The challenge has not been created
	}
	
	public List<Challenge> getChallenges() {
		return ChallengeDAO.getInstance().findAll();
	}
	
	public boolean acceptChallenge(User user, String challengeName) {

		if (user != null) {
			
            for (Challenge challenge : getActiveChallenges(user, Calendar.getInstance().getTimeInMillis()+"")) {
                if (challenge.getName().equals(challengeName)) {
                	user.addAcceptedChallenge(challenge);
                	UserDAO.getInstance().store(user);
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

	            for (Challenge challenge : ChallengeDAO.getInstance().findAll()) {
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
		return new ArrayList<>(user.getAcceptedChallengeList()); // The list with the accepted challenges of that concrete user
	}
}
