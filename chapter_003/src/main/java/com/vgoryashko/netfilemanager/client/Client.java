package com.vgoryashko.netfilemanager.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Class that implements main logic of the client application for the network file manager.
 *
 * @author Vlad Goryashko
 * @version 0.11
 * @since 3/17/17
 */
public class Client {

    private Socket socket;

    public Client(Socket aSocket) {
        this.socket = aSocket;
    }

    File dwnFile = null;
    File uplFile = null;

    public void upload(DataOutputStream aOut) {

        try (RandomAccessFile upload = new RandomAccessFile(uplFile, "rw")) {
            int data;
            do {
                data = upload.read();
                aOut.write(data);
            } while (data != -1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void download(DataInputStream aIn) {

        try (RandomAccessFile download = new RandomAccessFile(dwnFile, "rw")) {

            int data;
            do {
                data = aIn.read();
                if (data != 255) {
                    download.write(data);
                }
            } while (data != 255);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void start() {

        try (DataInputStream in = new DataInputStream(this.socket.getInputStream());
             DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readUTF());

            String command;
            String response;

            do {

                System.out.println(in.readUTF());
                command = scanner.nextLine();

                if ("exit".equals(command)) {

                    out.writeUTF(command);
                    response = in.readUTF();
                    System.out.println(response);

                } else if (command.startsWith("upl ")) {

                    String filePath = command.substring("upl ".length(), command.length());
                    uplFile = new File(filePath);
                    String fileName = uplFile.getName();
                    out.writeUTF(String.format("upl %s", fileName));
                    upload(out);
                    System.out.println(in.readUTF());

                } else if (command.startsWith("dwn ")) {
                    String filePath = command.substring("dwn ".length(), command.length());
                    dwnFile = new File(filePath);
                    out.writeUTF(command);
                    download(in);
                    out.writeUTF("File received successfully.");

                } else {

                    out.writeUTF(command);

                    do {
                        response = in.readUTF();
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
