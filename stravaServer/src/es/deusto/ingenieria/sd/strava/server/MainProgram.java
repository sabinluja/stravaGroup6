package es.deusto.ingenieria.sd.strava.server;

import java.rmi.Naming;
import java.rmi.Remote;

import javax.swing.SwingUtilities;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateways.FacebookGateway;
import es.deusto.ingenieria.sd.strava.server.gateways.GoogleGateway;
import es.deusto.ingenieria.sd.strava.server.gateways.GoogleGateway2;
import es.deusto.ingenieria.sd.strava.server.gateways.IProviderGateway;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.SessionDAO;
import es.deusto.ingenieria.sd.strava.server.jpa.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.remote.IRemoteFacade;
import es.deusto.ingenieria.sd.strava.server.remote.RemoteFacade;


public class MainProgram {


    public static void main(String[] args) {


        //args[0] = RMIRegistry IP
        //args[1] = RMIRegistry Port
        //args[2] = Service Name
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
        
        initDB();

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
            IProviderGateway facebookClients = FacebookGateway.getInstance(args[4], Integer.parseInt(args[5]));
            // IProviderGateway facebookClient = FacebookGateway.getInstance();

            IProviderGateway googleClient = GoogleGateway.getInstance();
            //System.out.println("URL: " + "http://127.0.0.1:8888/");
            //IProviderGateway googleClient = new GoogleGateway("http://127.0.0.1:8888/");
            SwingUtilities.invokeLater(() -> new GoogleGateway2("http://127.0.0.1:8888/"));
            googleClient.register("hola", "hola");

        } catch (Exception ex) {
            System.err.println(" # Strava Server Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
	public static void initDB() {
// CHALLENGES
		try {
        Challenge challenge1 = new Challenge();
        challenge1.setName("Challenge 1");
        challenge1.setStartDate("2023-01-01");
        challenge1.setEndDate("2024-01-10");
        challenge1.setTargetTime(3600); // 1 hour
        challenge1.setTargetDistance(10.0f); // 10 kilometers
        challenge1.setSports("Running");

        Challenge challenge2 = new Challenge();
        challenge2.setName("Challenge 2");
        challenge2.setStartDate("2023-02-01");
        challenge2.setEndDate("2024-02-15");
        challenge2.setTargetTime(1800); // 30 minutes
        challenge2.setTargetDistance(5.0f); // 5 kilometers
        challenge2.setSports("Cycling");

        Challenge challenge3 = new Challenge();
        challenge3.setName("Challenge 3");
        challenge3.setStartDate("2023-03-01");
        challenge3.setEndDate("2023-03-15");
        challenge3.setTargetTime(5400); // 1.5 hours
        challenge3.setTargetDistance(3.0f); // 3 kilometers
        challenge3.setSports("Cycling");

        Challenge challenge4 = new Challenge();
        challenge4.setName("Challenge 4");
        challenge4.setStartDate("2023-04-01");
        challenge4.setEndDate("2024-04-20");
        challenge4.setTargetTime(7200); // 2 hours
        challenge4.setTargetDistance(8.0f); // 8 kilometers
        challenge4.setSports("Both");

        Challenge challenge5 = new Challenge();
        challenge5.setName("Challenge 5");
        challenge5.setStartDate("2023-05-01");
        challenge5.setEndDate("2023-05-30");
        challenge5.setTargetTime(10800); // 3 hours
        challenge5.setTargetDistance(20.0f); // 20 kilometers
        challenge5.setSports("Cycling");
        
        ChallengeDAO.getInstance().store(challenge1);
        ChallengeDAO.getInstance().store(challenge2);
        ChallengeDAO.getInstance().store(challenge3);
        ChallengeDAO.getInstance().store(challenge4);
        ChallengeDAO.getInstance().store(challenge5);
        

        // SESSIONS
        
        Session session1 = new Session();
        session1.setTitle("Session 1");
        session1.setSports("Running");
        session1.setDistance(5.0f); // 5 kilometers
        session1.setStartDate("2023-01-05");
        session1.setStartTime(System.currentTimeMillis());
        session1.setDuration(1800); // 30 minutes

        Session session2 = new Session();
        session2.setTitle("Session 2");
        session2.setSports("Cycling");
        session2.setDistance(15.0f); // 15 kilometers
        session2.setStartDate("2023-02-10");
        session2.setStartTime(System.currentTimeMillis());
        session2.setDuration(3600); // 1 hour

        Session session3 = new Session();
        session3.setTitle("Session 3");
        session3.setSports("Running");
        session3.setDistance(2.0f); // 2 kilometers
        session3.setStartDate("2023-03-10");
        session3.setStartTime(System.currentTimeMillis());
        session3.setDuration(2700); // 45 minutes

        Session session4 = new Session();
        session4.setTitle("Session 4");
        session4.setSports("Running");
        session4.setDistance(10.0f); // 10 kilometers
        session4.setStartDate("2023-04-15");
        session4.setStartTime(System.currentTimeMillis());
        session4.setDuration(7200); // 2 hours

        Session session5 = new Session();
        session5.setTitle("Session 5");
        session5.setSports("Both");
        session5.setDistance(30.0f); // 30 kilometers
        session5.setStartDate("2023-06-01");
        session5.setStartTime(System.currentTimeMillis());
        session5.setDuration(7200); // 2 hours
        
        SessionDAO.getInstance().store(session1);
        SessionDAO.getInstance().store(session2);
        SessionDAO.getInstance().store(session3);
        SessionDAO.getInstance().store(session4);
        SessionDAO.getInstance().store(session5);
        
        // USERS
        
        User user1 = new User();
        user1.setName("Sabin");
        user1.setEmail("sabin.luja@opendeusto.es");
        user1.setBirthDate("2003-01-01");
        user1.setProvider("Facebook");
        
        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setBirthDate("1995-05-15");
        user2.setProvider("Facebook");

        User user3 = new User();
        user3.setName("Alice Johnson");
        user3.setEmail("alice.johnson@example.com");
        user3.setBirthDate("1988-09-20");
        user3.setProvider("Google");

        User user4 = new User();
        user4.setName("Bob Anderson");
        user4.setEmail("bob.anderson@example.com");
        user4.setBirthDate("1992-12-08");
        user4.setProvider("Facebook");

        User user5 = new User();
        user5.setName("Eva Rodriguez");
        user5.setEmail("eva.rodriguez@example.com");
        user5.setBirthDate("1997-03-25");
        user5.setProvider("Google");

        // USERS
        user1.addChallenge(challenge1);
        user1.addChallenge(challenge4);
        user1.addAcceptedChallenge(challenge1);
        user1.addSession(session1);
        session1.setUser(user1);

        user2.addChallenge(challenge2);
        user2.addChallenge(challenge1);
        user2.addAcceptedChallenge(challenge2);
        user2.addSession(session2);
        session2.setUser(user2);

        user3.addChallenge(challenge3);
        user3.addChallenge(challenge5);
        user3.addAcceptedChallenge(challenge3);
        user3.addSession(session3);
        session3.setUser(user3);

        user4.addChallenge(challenge4);
        user4.addChallenge(challenge2);
        user4.addAcceptedChallenge(challenge4);
        user4.addSession(session4);
        session4.setUser(user4);

        user5.addChallenge(challenge5);
        user5.addChallenge(challenge3);
        user5.addAcceptedChallenge(challenge5);
        user5.addSession(session5);
        session5.setUser(user5);
        
        UserDAO.getInstance().store(user1);
        UserDAO.getInstance().store(user2);
        UserDAO.getInstance().store(user3);
        UserDAO.getInstance().store(user4);
        UserDAO.getInstance().store(user5);
        
        
	} catch (Exception ex) {
		System.out.println("\t$ Error storing data:" + ex.getMessage());
	}	
	}


}