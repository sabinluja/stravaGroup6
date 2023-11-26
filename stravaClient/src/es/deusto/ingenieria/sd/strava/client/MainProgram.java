package es.deusto.ingenieria.sd.strava.client;

import es.deusto.ingenieria.sd.strava.client.controller.UserController;
import es.deusto.ingenieria.sd.strava.client.controller.ChallengeController;
import es.deusto.ingenieria.sd.strava.client.controller.SessionController;
import es.deusto.ingenieria.sd.strava.client.gui.UserWindow;
import es.deusto.ingenieria.sd.strava.client.gui.ChallengeWindow;
import es.deusto.ingenieria.sd.strava.client.gui.Register;
import es.deusto.ingenieria.sd.strava.client.gui.SessionWindow;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class MainProgram {
	
	private ChallengeController challengeController;
	private SessionController sessionController;

	public static void main(String[] args) {	
		ServiceLocator serviceLocator = new ServiceLocator();
		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		ChallengeController challengeController = new ChallengeController(serviceLocator);			
		SessionController sessionController = new SessionController(serviceLocator);			
		UserController userController = new UserController(serviceLocator);
		ChallengeWindow challengeWindow = new ChallengeWindow(challengeController,userController);
		SessionWindow sessionWindow = new SessionWindow(sessionController,userController);
		UserWindow userWindow = new UserWindow(userController, challengeController, sessionController);			
	}

	public ChallengeController getChallengeController() {
		return challengeController;
	}

	public SessionController getSessionController() {
		return sessionController;
	}
	
}

// Sabin