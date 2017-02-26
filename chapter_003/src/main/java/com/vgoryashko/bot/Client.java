package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.9
 * @since 2/26/2017
 */
public class Client {

    /**
     * Variable that is referring to a socket.
     */
    private final Socket socket;

    /**
     * Constructor of the class.
     *
     * @param aSocket                        socket to be used in client application
     */
    public Client(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Method that implements basic logic of the client application.
     */
    public void clientStart() {

        final String bye = "bye";

        String clientCommand = null;
        String serverResponse = null;

        try (Scanner scanner = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream())) {

            System.out.println("Connecting to the server...");
            System.out.println(in.readLine());

            do {
                System.out.println("Enter a message to the server: ");

                clientCommand = scanner.nextLine();
                out.println(clientCommand);

                do {
                    serverResponse = in.readLine();
                    if (!serverResponse.isEmpty()) {
                        System.out.format("\"%s\"".concat(System.getProperty("line.separator")), serverResponse);
                    }
                } while (!serverResponse.isEmpty());

            } while (!clientCommand.equals(bye));

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method entry point for the application.
     *
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 4444)) {
            new Client(socket).clientStart();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
