package es.deusto.ingenieria.sd.strava.GoogleExternalService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.deusto.ingenieria.sd.strava.GoogleExternalService.service.GoogleService;

@RestController
public class GoogleController {

    private static final Logger log= LoggerFactory.getLogger(GoogleController.class);

    private GoogleService googleService;

    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    @GetMapping("/user/register/{email}/{password}")
    public boolean register(@PathVariable(value="email") String email, @PathVariable(value="password") String password) {
        log.info("Registering a user");
        return googleService.register(email, password);
    }

    @GetMapping("/validate/password/{email}/{password}")
    public boolean validatePassword(@PathVariable(value="email") String email, @PathVariable(value="password") String password) {
        log.info("Validating password");
        return googleService.validatePassword(email, password);
    }

    @GetMapping("/validate/email/{email}")
    public boolean validateEmail(@PathVariable(value="email") String email) {
    	log.info("Validating email");
        return googleService.validateEmail(email);
    }
}
