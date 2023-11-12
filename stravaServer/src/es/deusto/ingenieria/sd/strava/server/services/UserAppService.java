package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class UserAppService {
	
	public UserAppService() {}
	
	private List<User> registeredUsers = new ArrayList<User>();
	
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
		User user = new User();		
		user.setEmail("sabin.luja@opendeusto.es");
		user.setName("Sabin");
		
		//Generate the hash of the password
		String hashPass = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");	
		user.setPassword(hashPass);
		
		if (user.getEmail().equals(email) && user.checkPassword(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password))) {		
			return user;
		} else {
			return null;
		}
	}
}
