package com.vgoryashko.netfilemanager.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that implements main logic of the client application for the network file manager.
 *
 * @author Vlad Goryashko
 * @version 0.11
 * @since 3/17/17
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

        String fileUpload = String.format(".%spom.xml", File.separator);

        Command command = new Command("upl", "command");

        try (InputStream in = new BufferedInputStream(this.socket.getInputStream());
             OutputStream out = new BufferedOutputStream(this.socket.getOutputStream());
             FileInputStream fIn = new FileInputStream(new File(fileUpload));
             ObjectOutput oOut = new ObjectOutputStream(out)) {

            oOut.writeObject(command);

            while (fIn.available() > 0) {
                out.write(fIn.read());
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
