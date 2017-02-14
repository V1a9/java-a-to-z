package com.vgoryashko.bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/14/2017
 */
public class Client {

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {

        String ip = "127.0.0.1";
        String str = null;
        int port = 4444;

        try (Socket socket = new Socket(InetAddress.getByName(ip), port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connecting to the server...");
            System.out.println(in.readLine());
            System.out.println("Enter a message: ");

            while (true) {
                str = scanner.nextLine();
                out.println(str);
                str = in.readLine();
                System.out.format("Reverse message was received \"%s\"", str);
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }
}
