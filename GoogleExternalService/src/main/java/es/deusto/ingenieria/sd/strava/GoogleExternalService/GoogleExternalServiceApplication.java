package es.deusto.ingenieria.sd.strava.GoogleExternalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.deusto.ingenieria.sd.strava.GoogleExternalService.dao.UserRepository;
import es.deusto.ingenieria.sd.strava.GoogleExternalService.model.User;

@SpringBootApplication
public class GoogleExternalServiceApplication {
	
	private static final Logger log= LoggerFactory.getLogger(GoogleExternalServiceApplication.class);

	public static void main(String[] args) {
		try {
	        SpringApplication.run(GoogleExternalServiceApplication.class, args);
	    } catch (Exception e) {
	        log.error("Error al iniciar la aplicación.", e);
	    }
	}
	
	@Bean
    CommandLineRunner demo(UserRepository repository) {
      return (args) -> {
          // INIT data ... some Users
    	    	  
    	  User sabin = new User("sabin.luja@opendeusto.es", "sabin.luja");
    	  User alice = new User("alice.johnson@example.com", "alice.johnson");
    	  User eva = new User("eva.rodriguez@example.com", "eva.rodriguez");
    	  
    	  repository.save(sabin);
    	  repository.save(alice);
    	  repository.save(eva);
    	  
        log.info("Sample users created"); 
    	  
    	log.info("USER Services AVAILABLE ...");
      };
    }

}
