package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class UserAppService {
	
	User user1;
	private List<User> registeredUsers = new ArrayList<User>();
	
	public UserAppService() {
		initialize();
	}
	
	public void initialize() {

		// CHALLENGES
		
        Challenge challenge1 = new Challenge();
        challenge1.setName("Challenge 1");
        challenge1.setStartDate("2023-01-01");
        challenge1.setEndDate("2024-01-10");
        challenge1.setTargetTime(3600); // 1 hour
        challenge1.setTargetDistance(10.0f); // 10 kilometers
        challenge1.setSports("Running");

        Challenge challenge2 = new Challenge();
        challenge2.setName("Challenge 2");
        challenge2.setStartDate("2023-02-01");
        challenge2.setEndDate("2024-02-15");
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
        challenge4.setEndDate("2024-04-20");
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

        
        // SESIONS 
        
        Session session1 = new Session();
        session1.setTitle("Session 1");
        session1.setSports("Running");
        session1.setDistance(5.0f); // 5 kilometers
        session1.setStartDate("2023-01-05");
        session1.setStartTime(System.currentTimeMillis());
        session1.setDuration(1800); // 30 minutes

        Session session2 = new Session();
        session2.setTitle("Session 2");
        session2.setSports("Cycling");
        session2.setDistance(15.0f); // 15 kilometers
        session2.setStartDate("2023-02-10");
        session2.setStartTime(System.currentTimeMillis());
        session2.setDuration(3600); // 1 hour
        
        Session session3 = new Session();
        session3.setTitle("Session 3");
        session3.setSports("Running");
        session3.setDistance(2.0f); // 2 kilometers
        session3.setStartDate("2023-03-10");
        session3.setStartTime(System.currentTimeMillis());
        session3.setDuration(2700); // 45 minutes

        Session session4 = new Session();
        session4.setTitle("Session 4");
        session4.setSports("Running");
        session4.setDistance(10.0f); // 10 kilometers
        session4.setStartDate("2023-04-15");
        session4.setStartTime(System.currentTimeMillis());
        session4.setDuration(7200); // 2 hours

        Session session5 = new Session();
        session5.setTitle("Session 5");
        session5.setSports("Both");
        session5.setDistance(30.0f); // 30 kilometers
        session5.setStartDate("2023-06-01");
        session5.setStartTime(System.currentTimeMillis());
        session5.setDuration(7200); // 2 hours

        
        // USERS
        
        user1 = new User();
        user1.setName("Sabin");
        user1.setEmail("sabin.luja@opendeusto.es");
        user1.setBirthDate("2003-01-01");

        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setBirthDate("1995-05-15");
	
        User user3 = new User();
        user3.setName("Alice Johnson");
        user3.setEmail("alice.johnson@example.com");
        user3.setBirthDate("1988-09-20");

        User user4 = new User();
        user4.setName("Bob Anderson");
        user4.setEmail("bob.anderson@example.com");
        user4.setBirthDate("1992-12-08");

        User user5 = new User();
        user5.setName("Eva Rodriguez");
        user5.setEmail("eva.rodriguez@example.com");
        user5.setBirthDate("1997-03-25");
		
		user1.addChallenge(challenge1);
		user1.addChallenge(challenge4);
		user1.addAcceptedChallenge(challenge1);
		user1.addSession(session1);
		user1.addSession(session4);
		
		user2.addChallenge(challenge2);
		user2.addChallenge(challenge1);
		user2.addAcceptedChallenge(challenge2);
		user2.addSession(session2);
		user2.addSession(session1);
		
		user3.addChallenge(challenge3);
		user3.addChallenge(challenge5);
		user3.addAcceptedChallenge(challenge3);
		user3.addSession(session3);
		user3.addSession(session5);
		
		user4.addChallenge(challenge4);
		user4.addChallenge(challenge2);
		user4.addAcceptedChallenge(challenge4);
		user4.addSession(session4);
		user4.addSession(session2);
		
		user5.addChallenge(challenge5);
		user5.addChallenge(challenge3);
		user5.addAcceptedChallenge(challenge5);
		user5.addSession(session5);
		user5.addSession(session3);
		
		registeredUsers.add(user1);
		registeredUsers.add(user2);
		registeredUsers.add(user3);
		registeredUsers.add(user4);
		registeredUsers.add(user5);
		
	}
	

	
	// Mandatory arguments for registration
	
	public boolean registerGoogle(String email, String name, String birthDate) {
		try {
			User registerGoogleMandatory = new User(name, email, birthDate);
			registeredUsers.add(registerGoogleMandatory);
			
			return true;
	    } catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	public boolean registerFacebook(String email, String name, String birthDate) {
		try {
			User registerGoogleMandatory = new User(name, email, birthDate);
			registeredUsers.add(registerGoogleMandatory);
			
			return true;
	    } catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	// Mandatory + optional arguments for registration
	
	public boolean registerGoogle(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate) {
		try {
			User registerGoogleMandatory = new User(name, email, birthDate, weight, height, maxHeartRate, restHeartRate);
			registeredUsers.add(registerGoogleMandatory);
			
			return true;
	    } catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	public boolean registerFacebook(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate) {
		try {
			User registerGoogleMandatory = new User(name, email, birthDate, weight, height, maxHeartRate, restHeartRate);
			registeredUsers.add(registerGoogleMandatory);
			
			return true;
	    } catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	public User login(String email, String password) {
		
		//Generate the hash of the password
		//String hashPass = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");	
		//user1.setPassword(hashPass);
		User user = new User();
		for (User u : this.registeredUsers) {
			if(u.getEmail().equals(email)) {
				user.setName(u.getName());
		        user.setEmail(u.getEmail());
		        user.setBirthDate(u.getBirthDate());
		        user.setSessionList(u.getSessionList());
		        user.setAcceptedChallengeList(u.getAcceptedChallengeList());
		        user.setChallengeList(u.getChallengeList());
				String hashPass = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");	
				user.setPassword(hashPass);
			}
		}
		
		if (user.getEmail().equals(email) && user.checkPassword(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password))) {		
			return user;
		} else {
			return null;
		}
	}
}
