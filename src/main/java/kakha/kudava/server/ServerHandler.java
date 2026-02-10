package kakha.kudava.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerHandler {

    private final ServerSocket serverSocket;

    public ServerHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public void start() throws IOException {
        Socket clientSocket = serverSocket.accept();
        clientSocket.setSoTimeout(30_000);

        try (clientSocket;
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Client connected!");

            ServerActions serverAction = new ServerActions(null);

            while (true) {
                String message;
                try {
                    message = in.readLine();
                    if (message == null) {
                        break; // client disconnected
                    }

                    if (message.equals("QUIT")) {
                        out.println("Goodbye");
                        break;
                    }
                } catch (SocketTimeoutException e) {
                    out.println("TIMEOUT");
                    System.out.println("Client timed out!");
                    break;
                }



                serverAction.setMessage(message);
                String response = serverAction.messageChoices();
                System.out.println(response);
                out.println(response);
            }

            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
