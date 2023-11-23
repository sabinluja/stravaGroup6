package es.deusto.ingenieria.sd.strava.GoogleExternalService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.deusto.ingenieria.sd.strava.GoogleExternalService.service.GoogleService;

public class GoogleController {

    private static final Logger log= LoggerFactory.getLogger(GoogleController.class);

    private GoogleService googleService;


    @GetMapping("/user/register/{email}/{password}")
    public boolean register(@PathVariable String email, @PathVariable String password) {
        log.info("Registering a user");
        return this.googleService.register(email, password);
    }

    @GetMapping("/validate/password/{email}/{password}")
    public boolean validatePassword(@PathVariable String email, @PathVariable String password) {
        log.info("Validating password");
        return this.googleService.validatePassword(email, password);
    }

    @GetMapping("/validate/email/{email}")
    public boolean validateEmail(@PathVariable String email) {
    	log.info("Validating email");
        return this.googleService.validateEmail(email);
    }
}
