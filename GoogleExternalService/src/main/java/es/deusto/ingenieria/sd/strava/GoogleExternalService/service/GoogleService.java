package es.deusto.ingenieria.sd.strava.GoogleExternalService.service;

import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;

public class GoogleService implements IProviderGateway {

	@Override
	public boolean register(String email, String name, String birthDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String email, String name, String birthDate, float weight, int height, int maxHeartRate,
			int restHeartRate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
