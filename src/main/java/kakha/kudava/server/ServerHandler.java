package kakha.kudava.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {

    private final ServerSocket serverSocket;

    public ServerHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        try(serverSocket) {
            System.out.println("Server is running and waiting for client connection...");
            while(true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected!");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                ServerActions serverAction = new ServerActions(null);
                if (message != null){
                    System.out.println("Client: " + message);
                    serverAction.setMessage(message);
                    System.out.println(serverAction.messageChoices());
                    out.println(serverAction.messageChoices());
                }


                out.println("Message received by the server.");
            }

            // Close the client socket
            //clientSocket.close();
            //Close the server socket
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
