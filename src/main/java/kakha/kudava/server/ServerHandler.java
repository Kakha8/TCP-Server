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
        try(Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            System.out.println("Client connected!");
            while(true) {

                String message = in.readLine();
                ServerActions serverAction = new ServerActions(null);
                if (message != null){

                    if (message.equals("QUIT")){
                        clientSocket.close();
                        break;
                    }else{
                        serverAction.setMessage(message);
                        System.out.println(serverAction.messageChoices());
                        out.println(serverAction.messageChoices());
                    }

                }


            }

            System.out.println("Client disconnected!");

            // Close the client socket
            //clientSocket.close();
            //Close the server socket
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
