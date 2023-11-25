package es.deusto.ingenieria.sd.strava.FacebookExternalService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class RequestHandler {

    public void handleRequest(Socket socket) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String request = in.readLine(); // Read input from client

            System.out.println("Received request from client: " + request);

            in.close(); // Close the input stream
        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
                
    }
   
}