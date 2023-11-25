package es.deusto.ingenieria.sd.strava.FacebookExternalService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class FacebookService extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;
    private static final String DELIMITER = "#";
    private RequestHandler rh = new RequestHandler();

    
    public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
		    this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.err.println("# FacebookService - TCPConnection IO error:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = this.in.readUTF();			
			System.out.println("   - FacebookService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");					
			data = processRequest(data);
			this.out.writeUTF(data);					
			System.out.println("   - FacebookService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data.toUpperCase() + "'");
		} catch (EOFException e) {
			System.err.println("   # FacebookService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # FacebookService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
    
    
    public String processRequest(String request) {
    	String r;
        StringTokenizer tokenizer = new StringTokenizer(request, DELIMITER);
        String action = tokenizer.nextToken();
        
        //RequestHandler rh = new RequestHandler();
        switch (action) {
            case "register_mandatory":
                r= rh.registerUser(tokenizer.nextToken(), tokenizer.nextToken());
            case "validate_password":
                r=rh.validatePassword(tokenizer.nextToken(), tokenizer.nextToken());
            case "validate_email":
                r=rh.validateEmail(tokenizer.nextToken());
            default:
                r="false";
        }
        rh.handleRequest(this.tcpSocket);
        return r;
    }

    
}
