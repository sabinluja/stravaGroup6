package es.deusto.ingenieria.sd.strava.server.gateways;

import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FacebookGateway implements IProviderGateway {

	private static IProviderGateway instance;
	private String serverIP;
	private int serverPort;
	private static String DELIMITER = "#";
	
	private FacebookGateway() {
		try {		
			//String URL = "//127.0.0.1:1099/CurrencyExchange";
			//this.currencyConvService = (ICurrencyExchange) Naming.lookup(URL);
			this.serverIP = "127.0.0.1";
			this.serverPort = 1099;
		} catch (Exception ex) {
			System.err.println("# Error locating external service: " + ex);
		}
	}
	
	public static IProviderGateway getInstance() {
		if(instance == null) {
			instance = new FacebookGateway();
		}
		
		return instance;
	}
	
	@Override
	public boolean register(String email, String password) {
		String message = "register_mandatory"+DELIMITER+email+DELIMITER+password;
		String response = null;
		StringTokenizer tokenizer = null;
			
		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
		try (Socket socket = new Socket(serverIP, serverPort);
			//Streams to send and receive information are created from the Socket
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
			
			//Send request (one String) to the server
			out.writeUTF(message);
			System.out.println(" - Sending data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
			
			//Read response (one String) from the server
			response = in.readUTF();			
			System.out.println(" - Getting response from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + response + "'");
			tokenizer = new StringTokenizer(response, DELIMITER);

		} catch (UnknownHostException e) {
			System.err.println("# Trans. SocketClient: Socket error: " + e.getMessage());	
		} catch (EOFException e) {
			System.err.println("# Trans. SocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# Trans. SocketClient: IO error: " + e.getMessage());
		}
		//return translation;
		
		if (tokenizer.nextToken().equals("OK")) {
			return true;
		} else {
			return false;
		} 	
	}
		

	@Override
	public boolean validatePassword(String email, String password) {
		String message = "validate_password"+DELIMITER+email+DELIMITER+password;
		String response = null;
		StringTokenizer tokenizer = null;
			
		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
		try (Socket socket = new Socket(serverIP, serverPort);
			//Streams to send and receive information are created from the Socket
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
			
			//Send request (one String) to the server
			out.writeUTF(message);
			System.out.println(" - Sending data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
			
			//Read response (one String) from the server
			response = in.readUTF();			
			System.out.println(" - Getting response from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + response + "'");
			tokenizer = new StringTokenizer(response, DELIMITER);

		} catch (UnknownHostException e) {
			System.err.println("# Trans. SocketClient: Socket error: " + e.getMessage());	
		} catch (EOFException e) {
			System.err.println("# Trans. SocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# Trans. SocketClient: IO error: " + e.getMessage());
		}
		//return translation;
		
		if (tokenizer.nextToken().equals("OK")) {
			return true;
		} else {
			return false;
		} 
	}

	@Override
	public boolean validateEmail(String email) {
		String message = "validate_password"+DELIMITER+email;
		String response = null;
		StringTokenizer tokenizer = null;
			
		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
		try (Socket socket = new Socket(serverIP, serverPort);
			//Streams to send and receive information are created from the Socket
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
			
			//Send request (one String) to the server
			out.writeUTF(message);
			System.out.println(" - Sending data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
			
			//Read response (one String) from the server
			response = in.readUTF();			
			System.out.println(" - Getting response from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + response + "'");
			tokenizer = new StringTokenizer(response, DELIMITER);

		} catch (UnknownHostException e) {
			System.err.println("# Trans. SocketClient: Socket error: " + e.getMessage());	
		} catch (EOFException e) {
			System.err.println("# Trans. SocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# Trans. SocketClient: IO error: " + e.getMessage());
		}
		//return translation;
		
		if (tokenizer.nextToken().equals("OK")) {
			return true;
		} else {
			return false;
		} 
	}

}
