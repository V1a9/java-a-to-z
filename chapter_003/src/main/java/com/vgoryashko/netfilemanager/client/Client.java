package com.vgoryashko.netfilemanager.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Class that implements main logic of the client application for the network file manager.
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 3/22/17
 */
public class Client {

    /**
     * Variable that referring to a Socket instance.
     */
    private Socket socket;

    /**
     * Constructor for the class.
     * @param aSocket               <code>Socket</code>
     */
    public Client(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Variable that used for referring to a file to be downloaded.
     */
    File dwnFile = null;

    /**
     * Variable that used for referring to a file to be uploaded.
     */
    File uplFile = null;

    /**
     * Method that performs uploading of a file to a client.
     * @param aOut                       Output stream
     */
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

    /**
     * Method that performs downloading of a file to a client.
     * @param aIn                       Input stream
     */
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

    /**
     * Method that implements major logic of the Client application.
     */
    public void start() {

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

    /**
     * Main method entry point for the application.
     *
     * @param args                      standard argument for the main method
     */
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
