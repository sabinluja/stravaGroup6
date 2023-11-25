package es.deusto.ingenieria.sd.strava.GoogleExternalService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import es.deusto.ingenieria.sd.strava.GoogleExternalService.dao.UserRepository;
import es.deusto.ingenieria.sd.strava.GoogleExternalService.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class GoogleExternalServiceApplication {
	
	private static final Logger log= LoggerFactory.getLogger(GoogleExternalServiceApplication.class);
	 
	@Value("${spring.mail.host}")		
	private String host;
	@Value("${spring.mail.port}")		
	private int port;
	@Value("${spring.mail.username}")
    private String sender;
	@Value("${spring.mail.password}")
	private String password;
	 

	public static void main(String[] args) {
		SpringApplication.run(GoogleExternalServiceApplication.class, args);
		 
	}
	
	@Bean
    JavaMailSender getJavaMailSender() {
    	// Configuration programmatically done - automatic Spring mapping does not work
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); 
       
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(sender);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
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
