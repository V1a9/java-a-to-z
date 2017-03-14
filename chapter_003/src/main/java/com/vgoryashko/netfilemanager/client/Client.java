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

        try (InputStream in = new BufferedInputStream(this.socket.getInputStream());
             OutputStream out = new BufferedOutputStream(this.socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String command;
            String response;
            File dwnFile;
            File uplFile ;
            boolean uplReady = false;
            boolean dwnReady = false;

            if (reader.ready()) {
                int c;
                StringBuilder answer = new StringBuilder();
                do {
                    c = reader.read();
                    if (c != -1) {
                        answer.append((char) c);
                    }
                } while (c != -1);
                System.out.println(answer.toString());
            }

            response = reader.readLine();
            System.out.println(response);

            do {

                System.out.println(reader.readLine());
                System.out.println(reader.readLine());
                command = stdIn.readLine();

                if ("exit".equals(command)) {

                    writer.write(command);
                    response = reader.readLine();
                    System.out.println(response);

                } else if (command.startsWith("upl ")) {

                    String filePath = command.substring("upl ".length(), command.length());
                    uplFile = new File(filePath);

                    String fileName = uplFile.getName();
                    writer.write(String.format("upl %s", fileName));

                    do {
                        response = reader.readLine();
                        if (!response.isEmpty()) {
                            System.out.println(response);
                        }
                    } while (!response.isEmpty());

                    System.out.println(reader.readLine());
                    writer.write("Sending file.");

                    try (FileInputStream fileIn = new FileInputStream(uplFile)) {

                        int data;

                        do {
                            data = fileIn.read();
                            writer.write(data);
                        } while (data != -1);

                    } catch (IOException ioe) {
                        System.out.println("IO problems wile communicating with server");
                        ioe.printStackTrace();
                    }

                } else {

                    writer.write(command);

                    do {
                        response = reader.readLine();
                        if (!response.isEmpty()) {
                            System.out.println(response);
                        }
                    } while (!response.isEmpty());
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
