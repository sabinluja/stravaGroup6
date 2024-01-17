package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;

public interface IChallengeDAO {

    void create(Challenge challenge);

    Challenge read(String name);

    void update(Challenge challenge);

    void delete(Challenge challenge);
}
