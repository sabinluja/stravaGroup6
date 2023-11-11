package es.deusto.ingenieria.sd.strava.client;

import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.strava.client.controller.UserController;
import es.deusto.ingenieria.sd.strava.client.controller.ChallengeController;
import es.deusto.ingenieria.sd.strava.client.controller.SessionController;
import es.deusto.ingenieria.sd.strava.client.gui.UserWindow;
import es.deusto.ingenieria.sd.strava.client.gui.ChallengeWindow;
//import es.deusto.ingenieria.sd.strava.client.gui.SessionWindow;
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
		
		UserController userController = new UserController(serviceLocator);
		UserWindow userWindow = new UserWindow(userController);			
		ChallengeController challengeController = new ChallengeController(serviceLocator);			
		ChallengeWindow challengeWindow = new ChallengeWindow(challengeController);
		SessionController sessionController = new SessionController(serviceLocator);			
		//SessionWindow sessionWindow = new SessionWindow(SessionController);
		
		
		
		//Login
		loginDialog.login();		
		//Get Categories
		List<CategoryDTO> categories = bidWindow.getCategories();
		//Get Articles of a category (first category is selected)
		List<ArticleDTO> articles = bidWindow.getArticles(categories.get(0).getName());
		//Convert currency to GBP
		bidWindow.currencyToGBP(articles);
		//Convert currency to USD
		bidWindow.currencyToUSD(articles);
		//Place a bid (first article of the category is selected; the token is stored in the BidController)
		bidWindow.makeBid(loginController.getToken(), articles.get(0));
		//Get Articles to check if the bid has been done
		articles = bidWindow.getArticles(categories.get(0).getName());
		//Logout
		loginDialog.logout();
	}

	public ChallengeController getChallengeController() {
		return challengeController;
	}

	public SessionController getSessionController() {
		return sessionController;
	}
	
}

// Sabin