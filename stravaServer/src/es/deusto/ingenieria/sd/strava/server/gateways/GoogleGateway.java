package es.deusto.ingenieria.sd.strava.server.gateways;

public class GoogleGateway implements IProviderGateway {

	private static GoogleGateway instance;
	private IProviderGateway googleProvide;
	
	private GoogleGateway() {
		
	}
	
	public static GoogleGateway getInstance() {
		if(instance == null) {
			instance = new GoogleGateway();
		}
		
		return instance;
	}
	
	public boolean register(String email, String name, String birthDate) {


		return false;
	}

	
	public boolean register(String email, String name, String birthDate, float weight, int height, int maxHeartRate, int restHeartRate) {
		
		
		return false;
	}

	
	
	public boolean validatePassword(String email, String password) {
		
		
		return false;
	}

	
	
	public boolean validateEmail(String email) {

		
		return false;
	}

}
