package es.deusto.ingenieria.sd.strava.FacebookExternalService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FacebookServer {

    private int port;

    public FacebookServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Facebook Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                // Delegate handling to a handler or controller
                RequestHandler handler = new RequestHandler(socket);
                handler.handleRequest();
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }
}
