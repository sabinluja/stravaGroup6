package es.deusto.ingenieria.sd.strava.GoogleExternalService.controller;

import org.springframework.web.bind.annotation.*;

public class GoogleController {

	@GetMapping("/user/register/{email}/{password}")
	public boolean register(@PathVariable String email, @PathVariable String password) {
		
		
		return false;
	}

	@GetMapping("/validate/password/{email}/{password}")
	public boolean validatePassword(@PathVariable String email, @PathVariable String password) {
		
		
		return false;
	}

	@GetMapping("/validate/email/{email}")
	public boolean validateEmail(@PathVariable String email) {
		
		
		return false;
	}
} 
