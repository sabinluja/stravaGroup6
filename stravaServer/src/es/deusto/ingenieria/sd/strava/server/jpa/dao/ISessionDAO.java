package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;

public interface ISessionDAO {

    void create(Session session);

    Session read(String title);

    void update(Session session);

    void delete(Session session);
}
