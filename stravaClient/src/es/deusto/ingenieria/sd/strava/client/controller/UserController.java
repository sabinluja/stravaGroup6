package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class UserController {
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	private long token = -1; 

	public UserController (ServiceLocator serviceLoc) {
		this.serviceLocator = serviceLoc;
	}
	
	public boolean register(String email, String name, String birthDate, String password, String provider) {
		try {
			return ((UserController) this.serviceLocator.getService()).register(email, name, birthDate, password, provider);
		} catch (Exception e) {
			System.out.println("# Error registering with google (only mandatory arguments): " + e);
			return false;
		}
    }
    
    
    public boolean register(String email, String name, String birthDate, String password, String provider,
                                  float weight, int height, int maxHeartRate, int restHeartRate) {
    	try {
			return ((UserController) this.serviceLocator.getService()).register(email, name, birthDate, password, provider, weight, height, maxHeartRate, restHeartRate);
		} catch (Exception e) {
			System.out.println("# Error registering with google (mandatory + optional arguments): " + e);
			return false;
		}
    }
    
    
    public boolean login(String email, String password) {
		try {
			this.token = this.serviceLocator.getService().login(email, password);
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error in login (mandatory + optional arguments): " + e);
			return false;
		}
	}
    
    public void logout() {
    	try {
			this.serviceLocator.getService().logout((this.getToken())+"");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public long getToken() {
		return token;
	}
}
