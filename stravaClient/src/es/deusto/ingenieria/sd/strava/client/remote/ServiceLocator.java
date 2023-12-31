package es.deusto.ingenieria.sd.strava.client.remote;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.strava.server.remote.IRemoteFacade;

//This class implements Service Locator pattern
public class ServiceLocator {

    //Remote Facade reference
    private IRemoteFacade service;

    public void setService(String ip, String port, String serviceName) {


        //Get Remote Facade reference using RMIRegistry (IP + Port) and the service name.
        try {
            String URL = "//" + ip + ":" + port + "/" + serviceName;
            System.out.println();
            this.service =  (IRemoteFacade) Naming.lookup(URL);
            System.out.println(service.getClass());
            System.out.println(service.toString());
        } catch (Exception ex) {
            System.err.println("# Error locating remote facade: " + ex);
        }
    }

    public IRemoteFacade getService() {
        return this.service;
    }
}