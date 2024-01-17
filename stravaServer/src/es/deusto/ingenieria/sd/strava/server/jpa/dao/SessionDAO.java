package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SessionDAO implements ISessionDAO{

    private EntityManagerFactory emf;

    public SessionDAO(){
        emf = Persistence.createEntityManagerFactory("Strava");
    }

    @Override
    public void create(Session session) {
        storeObject(session);
    }

    @Override
    public Session read(String title) {
        EntityManager em = emf.createEntityManager();
        return em.find(Session.class, title);
    }

    @Override
    public void update(Session session) {
        storeObject(session);
    }

    @Override
    public void delete(Session session) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(session);
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
