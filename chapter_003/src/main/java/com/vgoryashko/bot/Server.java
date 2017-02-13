package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
//import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/13/2017
 */
public class Server {

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {

        int port = 4444;

        System.out.println("Waiting for a connection.....");

        try (Socket socket = new ServerSocket(port).accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connection is established successfully!");

            while (true) {

                System.out.println("Waiting for a command ...");

                String ask = in.readLine();
                System.out.println(ask);

                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm an oracle.");
                    out.println();
                }

                if ("exit".equals(ask)) {
                    System.out.println("Server is terminating....");
                    out.println("Server is terminating....");
                    break;
                }
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
