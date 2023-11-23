package es.deusto.ingenieria.sd.strava.GoogleExternalService.service;

import java.util.HashMap;

import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;

public class GoogleService implements IProviderGateway {

    private HashMap<String, String> registeredUser = new HashMap<>(); //key:email value:password

    @Override
    public boolean register(String email, String password) {
        registeredUser.put(email, password);
        return true;
    }

    @Override
    public boolean validatePassword(String email, String password) {
        if (!registeredUser.containsKey(email)) { 
            return false;
        } else {
            String registeredPassword = registeredUser.get(email);
            if (password.equals(registeredPassword)) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean validateEmail(String email) { //if the email is already registered return false
        if(registeredUser.containsKey(email)) {
            return false;
        } else {
            return true;
        }
    }

}