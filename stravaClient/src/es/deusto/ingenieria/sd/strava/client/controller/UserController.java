package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class UserController {
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;

	public UserController (ServiceLocator serviceLoc) {
		this.serviceLocator = serviceLoc;
	}
	
	public boolean registerGoogle(String email, String name, String birthDate) {
		try {
			return this.serviceLocator.getService().registerGoogle(email, name, birthDate);
		} catch (RemoteException e) {
			System.out.println("# Error registering with google (only mandatory arguments): " + e);
			return false;
		}
    }
    
    public boolean registerFacebook(String email, String name, String birthDate) {
    	try {
			return this.serviceLocator.getService().registerFacebook(email, name, birthDate);
		} catch (RemoteException e) {
			System.out.println("# Error registering with facebook (only mandatory arguments): " + e);
			return false;
		}
    }
    
    public boolean registerGoogle(String email, String name, String birthDate,
                                  float weight, int height, int maxHeartRate, int restHeartRate) {
    	try {
			return this.serviceLocator.getService().registerGoogle(email, name, birthDate, weight, height, maxHeartRate, restHeartRate);
		} catch (RemoteException e) {
			System.out.println("# Error registering with google (mandatory + optional arguments): " + e);
			return false;
		}
    }
    
    public boolean registerFacebook(String email, String name, String birthDate,
                                    float weight, int height, int maxHeartRate, int restHeartRate) {
    	try {
			return this.serviceLocator.getService().registerGoogle(email, name, birthDate, weight, height, maxHeartRate, restHeartRate);
		} catch (RemoteException e) {
			System.out.println("# Error registering with facebook (mandatory + optional arguments): " + e);
			return false;
		}
    }
}
