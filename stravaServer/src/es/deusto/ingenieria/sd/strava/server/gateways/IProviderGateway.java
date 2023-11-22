package es.deusto.ingenieria.sd.strava.server.gateways;

public interface IProviderGateway {
	boolean register(String email, String name, String birthDate);
    boolean register(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate);
    boolean validatePassword(String email, String password);
    boolean validateEmail(String email);
}
