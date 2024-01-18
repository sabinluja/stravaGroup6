package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;


//This class implements Singleton and DAO patterns
public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {

	private static ChallengeDAO instance;	
	
	private ChallengeDAO() { }
	
	public static ChallengeDAO getInstance() {
		if (instance == null) {
			instance = new ChallengeDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void store(Challenge object) {
		Challenge storedObject = instance.find(String.valueOf(object.getName()));

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
			System.out.println("  $ Error storing Challenge: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@Override
	public void delete(Challenge object) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Challenge storedObject = (Challenge) em.find(Challenge.class, 
													 String.valueOf(object.getName()));
			em.remove(storedObject);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error removing an Challenge: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Challenge> findAll() {				
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		List<Challenge> Challenges = new ArrayList<>();
		
		try {
			tx.begin();
			
			Query q = em.createQuery("SELECT a FROM Challenge a");
			Challenges = (List<Challenge>) q.getResultList();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Challenges: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return Challenges;
	}

	@Override
	public Challenge find(String param) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Challenge result = null; 

		try {
			tx.begin();
						
			result = (Challenge) em.find(Challenge.class, param);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an Challenge by Id: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			em.close();
		}

		return result;
	}


}