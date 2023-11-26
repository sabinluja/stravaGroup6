package es.deusto.ingenieria.sd.strava.GoogleExternalService.service;

import es.deusto.ingenieria.sd.strava.GoogleExternalService.dao.UserRepository;
import es.deusto.ingenieria.sd.strava.GoogleExternalService.model.User;
import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;

import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class GoogleService implements IProviderGateway {

	private UserRepository userRepository;
    
    public GoogleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
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

    @Override
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

    @Override
    public boolean validateEmail(String email) { //if the email is already registered return false
    	Optional<User> result = userRepository.findByEmail(email);
    	if (result == null) {
    		return true;
    	} else {
    		return false;
    	}
    }

}