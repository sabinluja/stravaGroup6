package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;


//This class implements Singleton and DAO patterns
public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User> {

	private static UserDAO instance;	
	
	private UserDAO() { }
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void store(User object) {
		User storedObject = instance.find(String.valueOf(object.getEmail()));

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			if (storedObject != null) {
				em.merge(object);
			} else {
				em.persist(object);			
			}
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing User: " + ex.getStackTrace() + "    " + ex.getLocalizedMessage() + "    " + ex.getMessage() + "    " + ex.hashCode() + "    " + ex.getCause() + "    " + ex.getClass());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@Override
	public void delete(User object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			User storedObject = (User) em.find(User.class, 
													 String.valueOf(object.getName()));
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error removing an User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {				
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		List<User> Users = new ArrayList<>();
		
		try {
			tx.begin();
			
			Query q = em.createQuery("SELECT a FROM User a");
			Users = (List<User>) q.getResultList();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return Users;
	}

	@Override
	public User find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		User result = null; 

		try {
			tx.begin();
						
			result = (User) em.find(User.class, param);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an User by email: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return result;
	}


}