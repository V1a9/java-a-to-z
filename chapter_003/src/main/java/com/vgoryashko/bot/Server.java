package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
//import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/16/2017
 */
public class Server {

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {

        String clientCommand = null;
        final String bye = "bye";
        int port = 4444;

        System.out.println("Waiting for a connection.....");

        try (Socket socket = new ServerSocket(port).accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("The connection was established successfully!");
            out.println("The connection was established successfully!");

            do {
                System.out.println("Waiting for a message...");

                do {
                    clientCommand = in.readLine();
                    System.out.format("The next message was received \"%s\"".concat(System.getProperty("line.separator")), clientCommand);
                    out.println(clientCommand.concat(System.getProperty("line.separator")));
                } while (!clientCommand.isEmpty());

                out.println(clientCommand.concat(System.getProperty("line.separator")));

//                if (clientCommand.equals(bye)) {
//                    System.out.println("Server is shutting down... Good bye!");
//                    out.println("Server is shutting down...");
//                } else if (clientCommand.equals("hello")) {
//                    out.println("Hello, dear friend, I'm an oracle!");
//                } else {
//                    out.println(clientCommand.concat(System.getProperty("line.separator")));
//                }

            } while (!clientCommand.equals("exit"));

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
