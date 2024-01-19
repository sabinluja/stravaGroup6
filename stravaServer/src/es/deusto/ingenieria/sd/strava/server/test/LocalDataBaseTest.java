package es.deusto.ingenieria.sd.strava.server.test;

import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.SessionDAO;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.UserDAO;

public class LocalDataBaseTest {

	public static void main(String[] args) {		
		//Initialize DB
		es.deusto.ingenieria.sd.strava.server.MainProgram.initDB();
		
		List<Challenge> challenges = null;
		List<Session> Sessions = null;
		List<User> users = null;
		
		//Retreiving objects from DB
		try {
			System.out.println(" - Retreiving all challenges ...");
			challenges = ChallengeDAO.getInstance().findAll();			
			System.out.println("\t- " + challenges.size() + " challenges retreived!");
			challenges.forEach(cat -> System.out.println("\t\t- " + cat));
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving all challenges: " + ex.getMessage());
		}

		try {
			System.out.println(" - Retreiving all Sessions ...");
			Sessions = SessionDAO.getInstance().findAll();						
			System.out.println("\t- " + Sessions.size() + " Sessions retreived!");
			Sessions.forEach(art -> System.out.println("\t\t- " + art));	
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving all Sessions: " + ex.getMessage());
		}
			
		try {
			System.out.println(" - Retreiving all users ...");
			users = UserDAO.getInstance().findAll();
			System.out.println("\t- " + users.size() + " users retreived!");
			users.forEach(usr -> System.out.println("\t\t- " + usr));
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving all users: " + ex.getMessage());
		}


		try {
			if (challenges != null && !challenges.isEmpty()) {
				String name = challenges.get(0).getName();
				System.out.println(" - Retreiving a Challenge by named '" + name + "' ...");
				Challenge Challenge = ChallengeDAO.getInstance().find(name);

			}
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving a Challenge by name: " + ex.getMessage());
		}
			
		
		Session Session = null;
		User user = null;
				
		if (Sessions != null && !Sessions.isEmpty()) {			
			System.out.println(" - Retreiving an Session by number '" + Sessions.get(0).getTitle() + "' ...");
			Session = SessionDAO.getInstance().find(String.valueOf(Sessions.get(0).getTitle()));				
			
			if (Session != null) {
				System.out.println("\t- " + Session);
			}
		}
			
		if (users != null && !users.isEmpty()) {
			System.out.println(" - Retreiving an user by email '" + users.get(0).getEmail() + "' ...");
			user = UserDAO.getInstance().find(String.valueOf(users.get(0).getEmail()));
			
			if (user != null) {
				System.out.println("\t- " + user);
			}				
		}

		try {
			//Clean DB
			System.out.println(" - Deleting all challenges ...");
			challenges = ChallengeDAO.getInstance().findAll();
			challenges.forEach(cat -> ChallengeDAO.getInstance().delete(cat));
			
			System.out.println(" - Deleting all users ...");
			users = UserDAO.getInstance().findAll();
			users.forEach(usr -> UserDAO.getInstance().delete(usr));			
			
			System.out.println(" - Retreiving all challenges ...");
			challenges = ChallengeDAO.getInstance().findAll();			
			System.out.println("\t- " + challenges.size() + " challenges retreived!");
			
			System.out.println(" - Retreiving all Sessions ...");
			Sessions = SessionDAO.getInstance().findAll();						
			System.out.println("\t- " + Sessions.size() + " Sessions retreived!");
			
			System.out.println(" - Retreiving all users ...");
			users = UserDAO.getInstance().findAll();
			System.out.println("\t- " + users.size() + " users retreived!");

		} catch (Exception ex) {
			System.out.println("\t$ Testing DAO: " + ex.getMessage());
		}
	}
}