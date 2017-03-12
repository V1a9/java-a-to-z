package com.vgoryashko.netfilemanager.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that implements main logic of the client application for the network file manager.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/6/17
 */
public class Client {

    public Socket getSocket() {
        return this.socket;
    }

    private Socket socket;

    public Client(Socket aSocket) {
        this.socket = aSocket;
    }

    private void start() {

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ClientSettings clientSettings = new ClientSettings("app.properties");
        clientSettings.readProperties();

        try (Socket socket = new Socket(InetAddress.getByName(clientSettings.getIpAddress()), clientSettings.getPort())) {
            Client client = new Client(socket);
            client.start();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + clientSettings.getIpAddress());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    clientSettings.getIpAddress());
            System.exit(1);
        }
    }
}
