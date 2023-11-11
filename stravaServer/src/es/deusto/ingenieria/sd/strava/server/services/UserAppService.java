package es.deusto.ingenieria.sd.strava.server.services;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class UserAppService {
	
	public UserAppService() {}
	
	// Mandatory arguments for registration
	
	public boolean registerGoogle(String email, String name, String birthDate) {
		
		return false;
	}
	
	public boolean registerFacebook(String email, String name, String birthDate) {
		
		return false;
	}
	
	// Mandatory + optional arguments for registration
	
	public boolean registerGoogle(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate) {
		
		return false;
	}
	
	public boolean registerFacebook(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate) {
		
		return false;
	}
	
	public User login(String email, String password) {
		User user = new User();		
		user.setEmail("sabin.luja@opendeusto.es");
		user.setName("Sabin");
		
		//Generate the hash of the password
		String hashPass = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");		
		user.setPassword(hashPass);
		
		if (user.getEmail().equals(email) && user.checkPassword(password)) {		
			return user;
		} else {
			return null;
		}	
	}
}
