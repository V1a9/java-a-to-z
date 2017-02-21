package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Vlad Goryashko
 * @version 0.8
 * @since 2/19/2017
 */
public class Server {

    /**
     * Variable that referring to a socket.
     */
    private final Socket socket;

    /**
     * Constructor of the class.
     * @param socket                    socket to be used for the server
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Method that describes a main algorithm of the application.
     */
    public void serverLauncher() {
        String clientCommand = null;

        System.out.println("Waiting for a connection.....");

        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {

            System.out.println("The connection was established successfully!");
            out.println("Oracle: The connection was established successfully!");

            do {

                System.out.println("Waiting for a message...");

                clientCommand = in.readLine();

                if (clientCommand.equals("bye")) {
                    System.out.println("Server is shutting down...");
                    out.println("Oracle: Good by my friend.");
                    out.println("Server is shutting down....");
                    out.println();
                } else if (clientCommand.equals("hello")) {
                    out.println("Oracle: Hello, dear friend, I'm an oracle!");
                    out.println();
                } else if (clientCommand.equals("How r u?")) {
                    out.println("Oracle: Thanks, I'm fine!");
                    out.println();
                } else if (clientCommand.equals("How is your life?")) {
                    out.println("Oracle: The life is great!!!!");
                    out.println();
                } else if (clientCommand.equals("")) {
                    out.println("Oracle: you sent nothing. Please try again.");
                    out.println();
                } else {
                    out.println(String.format("Oracle: you sent following \"%s\".", clientCommand));
                    out.println();
                }

            } while (!clientCommand.equals("bye"));

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {

        try (Socket socket = new ServerSocket(4444).accept()) {
            new Server(socket).serverLauncher();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }
}
