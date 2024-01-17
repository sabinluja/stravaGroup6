package es.deusto.ingenieria.sd.strava.server.jpa;

import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.IUserDAO;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.UserDAO;

public class DAOTest_Users  {

	public static void main(String[] args) {
		IUserDAO userDAO = new UserDAO();		
		
		createUserTest(userDAO);
		getAllUsersTest(userDAO);
		updateUserTest(userDAO);
		deleteUserTest(userDAO);
	}
		
	protected static void createUserTest(IUserDAO userDAO) {		
		System.out.println(" - CREATE USER TEST: Creating 2 new users ... ");
		try {
			User user1 = new User("John Doe", "john.doe@example.com");
			userDAO.create(user1);
	
			User user2 = new User("Jane Doe", "jane.doe@example.com");
			userDAO.create(user2);
		} catch (Exception ex) {
			System.out.println(" $ Error creating a new user: " + ex.getMessage());
		}
	}
	
	protected static void getAllUsersTest(IUserDAO userDAO) {		
		System.out.println(" - GET ALL USERS: Retrieving all users ...");
		try {
			List<User> users = userDAO.getAllUsers();
			users.forEach(u -> System.out.println("        # " + u));
			
		} catch (Exception ex) {
			System.out.println(" $ Error getting all users: " + ex.getMessage());
		}
	}
	
	protected static void updateUserTest(IUserDAO userDAO) {	
		System.out.println(" - UPDATE USER: Updating a user");
		try {					
			User user = userDAO.read("john.doe@example.com");
			System.out.println("     - Original user: " + user);
			
			user.setName("Johnathan Doe");
			userDAO.update(user);
			
			User updatedUser = userDAO.read("john.doe@example.com");
			System.out.println("     - Updated user: " + updatedUser);
		} catch (Exception ex) {
			System.out.println(" $ Error updating a user: " + ex.getMessage());
		}
	}
	
	protected static void deleteUserTest(IUserDAO userDAO) {	
		System.out.println(" - DELETE USER: Deleting a user");
		try {					
			User user = userDAO.read("jane.doe@example.com");
			System.out.println("     - Deleting user: " + user);
			
			userDAO.delete(user);
		} catch (Exception ex) {
			System.out.println(" $ Error deleting a user: " + ex.getMessage());
		}
	}
}
