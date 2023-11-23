package es.deusto.ingenieria.sd.strava.server.gateways;

public interface IProviderGateway {
	boolean register(String email, String password);
    boolean validatePassword(String email, String password);
    boolean validateEmail(String email);
}
