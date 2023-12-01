package es.deusto.ingenieria.sd.strava.GoogleExternalService.service;

import es.deusto.ingenieria.sd.strava.GoogleExternalService.dao.UserRepository;
import es.deusto.ingenieria.sd.strava.GoogleExternalService.model.User;

import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class GoogleService{

	private UserRepository userRepository;
    
    public GoogleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public boolean register(String email, String password) {
    	User user = new User(email, password);
    	Optional<User> result = userRepository.findByEmail(user.getEmail());
		
		if (result.isEmpty()) {
			User savedUser = userRepository.save(user);
			if (savedUser != null) {
				return true;
			}
		}	
		return false;
    }

    public boolean validatePassword(String email, String password) {
    	Optional<User> result = userRepository.findByEmail(email);
    	if (result == null) {
    		return false;
    	} else {
    		if (result.get().getPassword().equals(password)) {
    			return true;
    		} else {
    			return false;
    		}
    	}	
    }

    public boolean validateEmail(String email) { //if the email is already registered return false
    	Optional<User> result = userRepository.findByEmail(email);
    	if (result.toString().equals("Optional.empty")) {
    		return true;
    	} else {
    		return false;
    	}
    }

}