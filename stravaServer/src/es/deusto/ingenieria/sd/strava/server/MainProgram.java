package es.deusto.ingenieria.sd.strava.server;

import java.rmi.Naming;
import java.rmi.Remote;

import es.deusto.ingenieria.sd.strava.server.gateways.FacebookGateway;
import es.deusto.ingenieria.sd.strava.server.gateways.GoogleGateway;
import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;
import es.deusto.ingenieria.sd.strava.server.remote.IRemoteFacade;
import es.deusto.ingenieria.sd.strava.server.remote.RemoteFacade;


public class MainProgram {

	
	public static void main(String[] args) {	


		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade();			
			Naming.rebind(name, (Remote) remoteFacade);
			System.out.println(" * Strava Server '" + name + "' started!!");
			
			if (args.length < 2) {
				System.err.println(" # Usage: Trans. SocketClient [SERVER IP] [PORT] ");
				System.exit(1);
			}
			
			// 4 y 5
			IProviderGateway facebookClients = new FacebookGateway(args[4], Integer.parseInt(args[5]));
			// IProviderGateway facebookClient = FacebookGateway.getInstance();
			
			IProviderGateway googleClient = GoogleGateway.getInstance();
			googleClient.register("hola", "hola");
			
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	

}
