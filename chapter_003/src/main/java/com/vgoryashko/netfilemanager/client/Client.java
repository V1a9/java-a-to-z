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

        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(in.readLine());

            String command;
            String responce;
            File dwnFile;
            File uplFile ;
            boolean uplReady = false;
            boolean dwnReady = false;

            do {

                System.out.println(in.readLine());
                System.out.println(in.readLine());
                command = stdIn.readLine();

                if ("exit".equals(command)) {

                    out.println(command);
                    responce = in.readLine();
                    System.out.println(responce);

                } else if (command.startsWith("upl ")) {

                    String filePath = command.substring("upl ".length(), command.length());
                    uplFile = new File(filePath);

                    String fileName = uplFile.getName();
                    out.println(String.format("upl %s", fileName));

                    do {
                        responce = in.readLine();
                        if (!responce.isEmpty()) {
                            System.out.println(responce);
                        }
                    } while (!responce.isEmpty());

                    System.out.println(in.readLine());

                    try (BufferedReader reader = new BufferedReader(new FileReader(uplFile));
                         BufferedWriter output = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                         BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {

                        String data;

                        do {
                            if ((data = reader.readLine()) != null) {
                                output.write(data);
                            }
                        } while (data != null);

                        System.out.println(input.readLine());

                    } catch (IOException ioe) {
                        System.out.println("IO problems wile communicating with server");
                        ioe.printStackTrace();
                    }

                } else {

                    out.println(command);

                    do {
                        responce = in.readLine();
                        if (!responce.isEmpty()) {
                            System.out.println(responce);
                        }
                    } while (!responce.isEmpty());
                }

            } while (!"exit".equals(command));
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
