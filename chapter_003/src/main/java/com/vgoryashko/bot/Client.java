package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/16/2017
 */
public class Client {

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {

        final String exit = "exit";

        String ip = "127.0.0.1";
        String clientCommand = null;
        String serverResponse = null;
        int port = 4444;

        try (Socket socket = new Socket(InetAddress.getByName(ip), port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connecting to the server...");
            System.out.println(in.readLine());
            System.out.println("Enter a message: ");

            do {

                do {
                    clientCommand = scanner.nextLine();
                    out.println(clientCommand);
                    serverResponse = in.readLine();
                    System.out.format("Reverse message was received \"%s\"".concat(System.getProperty("line.separator")), serverResponse);
                } while (!clientCommand.isEmpty());

            } while (!clientCommand.equals(exit));

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }
}
