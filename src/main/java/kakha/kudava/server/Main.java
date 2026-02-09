package kakha.kudava.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {

        // create a server socket on port number 9090
        ServerSocket serverSocket = new ServerSocket(9090);
        ServerHandler serverHandler = new ServerHandler(serverSocket);
        serverHandler.start();
    }
}