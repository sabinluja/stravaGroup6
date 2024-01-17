package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    @Override
    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<User> users = new ArrayList<User>();

        try {
            tx.begin();
            
            Query q = em.createQuery("SELECT u FROM User u");            
            users = (List<User>) q.getResultList();
            
            tx.commit();
        } catch (Exception ex) {
            System.out.println("   $ Error retrieving all users: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        return users;
    }

}
