package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UserDAO implements IUserDAO{

    private EntityManagerFactory emf;

    public UserDAO(){
        emf = Persistence.createEntityManagerFactory("Strava");
    }

    @Override
    public void create(User user) {
        storeObject(user);
    }

    @Override
    public User read(String email) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, email);
    }

    @Override
    public void update(User user) {
        storeObject(user);
    }

    @Override
    public void delete(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(user);
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
