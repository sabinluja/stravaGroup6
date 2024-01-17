package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ChallengeDAO {

    private EntityManagerFactory emf;

    public ChallengeDAO(){
        emf = Persistence.createEntityManagerFactory("Strava");
    }

    public void create(Challenge challenge) {
        storeObject(challenge);
    }

    public Challenge read(String name) {
        EntityManager em = emf.createEntityManager();
        return em.find(Challenge.class, name);
    }

    public void update(Challenge challenge) {
        storeObject(challenge);
    }

    public void delete(Challenge challenge) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(challenge);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("   $ DAO: Error deleting an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    private void storeObject(Object object) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("   * DAO: Storing an object: " + object);
            em.persist(object);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("   $ DAO: Error storing an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }
}
