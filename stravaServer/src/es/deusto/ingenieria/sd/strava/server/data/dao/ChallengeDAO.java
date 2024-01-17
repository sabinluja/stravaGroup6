package es.deusto.ingenieria.sd.strava.server.data.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ChallengeDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Challenge challenge) {
        em.persist(challenge);
    }

    public Challenge read(String name) {
        return em.find(Challenge.class, name);
    }

    public void update(Challenge challenge) {
        em.merge(challenge);
    }

    public void delete(Challenge challenge) {
        em.remove(challenge);
    }
}
