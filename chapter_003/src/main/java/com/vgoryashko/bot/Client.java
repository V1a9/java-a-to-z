package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.7
 * @since 2/17/2017
 */
public class Client {

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {

        final String bye = "bye";

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
}
