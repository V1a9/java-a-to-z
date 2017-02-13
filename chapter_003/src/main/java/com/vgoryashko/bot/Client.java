package com.vgoryashko.bot;

import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/13/2017
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

            do {
                out.println("Hello oracle");
                str = scanner.nextLine();
                if (!str.isEmpty()) {
                    System.out.println(str);
                    out.write(str);
                    System.out.println(in.readLine());
                }
            } while (true);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }
}
