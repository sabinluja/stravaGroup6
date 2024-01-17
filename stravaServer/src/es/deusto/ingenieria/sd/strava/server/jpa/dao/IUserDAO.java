package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public interface IUserDAO {

    void create(User user);

    User read(String email);
    
    List<User> getAllUsers();

    void update(User user);

    void delete(User user);
}
