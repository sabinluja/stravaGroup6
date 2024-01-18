package es.deusto.ingenieria.sd.strava.server.services;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateways.GatewayFactory;
import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.UserDAO;

public class UserAppService {
    public UserAppService() {
    }

    
    // Mandatory arguments for registration
    public boolean register(String email, String name, String birthDate, String password, String provider) {
        try {
            User registerGoogleMandatory = new User(name, email, birthDate, provider);

            IProviderGateway externalService = GatewayFactory.getInstance().createGateway(provider);
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
                            float weight, int height, int maxHeartRate, int restHeartRate) {
        try {
            User registerGoogleMandatory = new User(name, email, birthDate, weight, height, maxHeartRate, restHeartRate, provider);

            IProviderGateway externalService = GatewayFactory.getInstance().createGateway(provider);
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

    public User login(String email, String password) {
        User user = new User();

        for (User u : UserDAO.getInstance().findAll()) {
            if (u.getEmail().equals(email)) {
                user.setName(u.getName());
                user.setEmail(u.getEmail());
                user.setBirthDate(u.getBirthDate());
                user.setSessionList(u.getSessionList());
                user.setAcceptedChallengeList(u.getAcceptedChallengeList());
                user.setChallengeList(u.getChallengeList());
                user.setProvider(u.getProvider());
            }
        }
        
        System.out.println(user.getProvider());
        IProviderGateway externalService = GatewayFactory.getInstance().createGateway(user.getProvider());
        boolean validatePassword = externalService.validatePassword(email, password);

        if (user.getEmail().equals(email) && validatePassword) {
            return user;
        } else {
            return null;
        }
    }
}



