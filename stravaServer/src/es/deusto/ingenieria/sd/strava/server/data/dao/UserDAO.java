package es.deusto.ingenieria.sd.strava.server.data.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UserDAO implements IUserDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public User read(String email) {
        return em.find(User.class, email);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }
}
