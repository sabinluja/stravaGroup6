package es.deusto.ingenieria.sd.strava.server.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GoogleGateway implements IProviderGateway {

	private static GoogleGateway instance;
	private IProviderGateway googleProvide;
	
	private static final Logger log = LoggerFactory.getLogger(GoogleGateway.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	// Host and port NOT hard-coded: Defined in application.properties
	@Value("${spring.server.url}")
	private String serverURL;
	
	@Value("${server.port}")
	private int serverPort;
	
	private GoogleGateway() {}
	
	public static GoogleGateway getInstance() {
		if(instance == null) {
			instance = new GoogleGateway();
		}
		return instance;
	}
	
	public boolean register(String email, String name, String birthDate) {
		
		log.info("This is the endpoint: " + serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}"); 
		User user = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}", User.class, Map.of("email", "rebeca.cortazar@deusto.es"));
		log.info("/user/email/{email} - This is User: " + user.getId() + "name: " + user.getFirstName());
		return "getUserByEmail -  " + user.getFirstName()+" "+ user.getLastName();

		return false;
	}

	
	public boolean register(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate) {
		
		
		return false;
	}

	
	
	public boolean validatePassword(String email, String password) {
		
		
		return false;
	}

	
	
	public boolean validateEmail(String email) {

		
		return false;
	}

}
