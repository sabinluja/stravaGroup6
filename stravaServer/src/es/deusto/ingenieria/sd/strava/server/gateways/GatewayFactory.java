package es.deusto.ingenieria.sd.strava.server.gateways;

public class GatewayFactory {
	
	private static GatewayFactory instance;
	
	private GatewayFactory() {}
	
	public static GatewayFactory getInstance() {
		if(instance == null) {
			instance = new GatewayFactory();
		}
		return instance;
	}
	
	public IProviderGateway createGateway(String provider) {
		IProviderGateway gateway;
		
		if (provider.equals("Google")) {
			gateway = GoogleGateway.getInstance();
		} else {
			gateway = FacebookGateway.getInstance();
		}
		
		return gateway;
	}
	
}
