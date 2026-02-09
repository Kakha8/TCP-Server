package kakha.kudava.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String args[]) throws IOException
    {

        try (
                Socket socket = new Socket("localhost", 9090);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server.");

            String input;
            while ((input = console.readLine()) != null) {
                out.println(input);
                System.out.println("Server: " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
