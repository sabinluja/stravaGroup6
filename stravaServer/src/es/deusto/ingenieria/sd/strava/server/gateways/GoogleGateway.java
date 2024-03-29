package es.deusto.ingenieria.sd.strava.server.gateways;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Service
public class GoogleGateway implements IProviderGateway {
	
	private static final Logger log = LoggerFactory.getLogger(GoogleGateway.class);
	
	@Autowired
	private RestTemplate restTemplate;
	private static GoogleGateway instance;
	private IProviderGateway googleProvide;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// Host and port NOT hard-coded: Defined in application.properties
	//@Value("${spring.server.url}")
	//private String serverURL = "http://localhost";
	private String serverURL;
	
	//@Value("${server.port}")
	//private int serverPort = 8888;
	private int serverPort;	

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}


	public static void start() {
		SpringApplication.run(GoogleGateway.class);	
	}
	
	public GoogleGateway() {
		restTemplate = restTemplate();
	}
	
	@Autowired
	public void setGateway(GoogleGateway r) {
		 instance = r;
	 }
	public static GoogleGateway getGateway() {
		return instance;
	}
	@Autowired
	public void setRestTemplate (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public static GoogleGateway getInstance() {
		if(instance == null) {
			instance = new GoogleGateway();
		}
		return instance;
	}
	
	public boolean register(String email, String password) {
		log.info("This is the endpoint: " + serverURL + ":" + String.valueOf(serverPort) + "/user/register/{email}/{password}"); 
		boolean response = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/register/{email}/{password}", boolean.class, Map.of("email", email, "password", password));
		log.info("/user/register/{email}/{password} - Response of the registration: " + response);
		return response;
	}

	
	public boolean validatePassword(String email, String password) {
		log.info("This is the endpoint: " + serverURL + ":" + String.valueOf(serverPort) + "/validate/password/{email}/{password}"); 
		boolean response = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/validate/password/{email}/{password}", boolean.class, Map.of("email", email, "password", password));
		log.info("/validate/password/{email}/{password} - Response of the password validation: " + response);
		return response;
	}
	
	
	public boolean validateEmail(String email) {
		log.info("This is the endpoint: " + serverURL + ":" + String.valueOf(serverPort) + "/validate/email/{email}"); 
		boolean response = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/validate/email/{email}", boolean.class, Map.of("email", email));
		log.info("/validate/email/{email} - Response of the email validation: " + response);
		return response;
	}

}
