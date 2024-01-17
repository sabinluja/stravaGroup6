package es.deusto.ingenieria.sd.strava.server.data.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class SessionDAO implements ISessionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Session session) {
        em.persist(session);
    }

    @Override
    public Session read(String title) {
        return em.find(Session.class, title);
    }

    @Override
    public void update(Session session) {
        em.merge(session);
    }

    @Override
    public void delete(Session session) {
        em.remove(session);
    }
}
