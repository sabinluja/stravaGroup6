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
	
	public IProviderGateway createGateway(String provider, String google_url, String facebook_ip, int facebook_port) {
		//IProviderGateway gateway;
		
		switch (provider) {
		    case "Google":
		    	GoogleGateway gatewayG = GoogleGateway.getInstance();
		    	System.out.println(google_url);
		        String[] partes = google_url.split(":");
		        String server_url = partes[0] + ":" + partes[1];
		        String serverPort = partes[2].split("/")[0];
		        System.out.println("url " + server_url);
		        System.out.println("port " + serverPort);
		        
		        gatewayG.setServerURL(server_url);
		        gatewayG.setServerPort(Integer.parseInt(serverPort));
		        return (IProviderGateway) gatewayG;
		      
		        
		    default:
		        FacebookGateway gatewayF = (FacebookGateway) FacebookGateway.getInstance();
		        gatewayF.setServerIP(facebook_ip);
		        gatewayF.setServerPort(facebook_port);
		        return (IProviderGateway) gatewayF;
		}
		
	}
	
}
