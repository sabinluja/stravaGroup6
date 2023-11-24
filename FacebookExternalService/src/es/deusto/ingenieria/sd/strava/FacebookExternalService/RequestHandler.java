package es.deusto.ingenieria.sd.strava.FacebookExternalService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestHandler {

    private Socket socket;
    private FacebookService service;

    public RequestHandler(Socket socket) {
        this.socket = socket;
        this.service = new FacebookService();
    }

    public void handleRequest() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            String request = in.readUTF();
            System.out.println("Received request from client: " + request);

            boolean response = service.processRequest(request);

         // Convert boolean to string representation
            String booleanAsString = String.valueOf(response);

            // Write the string representation to the output stream
            out.writeUTF(booleanAsString);
            System.out.println("Sent response to client: " + response);

        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}