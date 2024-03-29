package es.deusto.ingenieria.sd.strava.server.jpa.dao;

import java.util.List;


public interface IDataAccessObject<DomainObject> {
	public void store(DomainObject object);
	public void delete(DomainObject object);
	public List<DomainObject> findAll();
	public DomainObject find(String param);
}
