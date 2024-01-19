package es.deusto.ingenieria.sd.strava.server.services;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateways.GatewayFactory;
import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;
import es.deusto.ingenieria.sd.strava.server.gateways.MailSender;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.UserDAO;

public class UserAppService {
    public UserAppService() {
    }

    
    // Mandatory arguments for registration
    public boolean register(String email, String name, String birthDate, String password, String provider, String google_url, String facebook_ip, int facebook_port) {
        try {
            User registerGoogleMandatory = new User(name, email, birthDate, provider);

            IProviderGateway externalService = GatewayFactory.getInstance().createGateway(provider, google_url, facebook_ip, facebook_port);
            boolean emailExists = externalService.validateEmail(email);
            boolean registration = externalService.register(email, password);

            if (!emailExists && registration) {
            	UserDAO.getInstance().store(registerGoogleMandatory);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mandatory + optional arguments for registration
    public boolean register(String email, String name, String birthDate, String password, String provider,
                            float weight, int height, int maxHeartRate, int restHeartRate, String google_url, String facebook_ip, int facebook_port) {
        try {
            User registerGoogleMandatory = new User(name, email, birthDate, weight, height, maxHeartRate, restHeartRate, provider);

            IProviderGateway externalService = GatewayFactory.getInstance().createGateway(provider, google_url, facebook_ip, facebook_port);
            boolean emailExists = externalService.validateEmail(email);
            boolean registration = externalService.register(email, password);

            if (emailExists && registration) {
            	UserDAO.getInstance().store(registerGoogleMandatory);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String email, String password, String google_url, String facebook_ip, int facebook_port) {
        
        User user = UserDAO.getInstance().find(email);
        
        System.out.println(user.getProvider());
        IProviderGateway externalService = GatewayFactory.getInstance().createGateway(user.getProvider(), google_url, facebook_ip, facebook_port);
        boolean validatePassword = externalService.validatePassword(email, password);

        if (user.getEmail().equals(email) && validatePassword) {
        	new MailSender(user.getEmail()).sendMessage("Log in was successfull.");
            return user;
        } else {
            return null;
        }
    }
}



