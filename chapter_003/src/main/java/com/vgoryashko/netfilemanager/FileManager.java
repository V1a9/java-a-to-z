package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/27/2017
 */
public class FileManager {

    /**
     * Variable that referring to an instance of a Socket class.
     */
    private Socket socket;

    /**
     * Constructor for the class.
     */
    public FileManager(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Method that implements basic logic of the file server application.
     */
    public void managerStart() {

        String command = null;
        String responce = null;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println(in.readLine());

            do {

                System.out.println(in.readLine());
                command = scanner.nextLine();

                if ("exit".equals(command)) {
                    out.println(command);
                } else {

                    out.println(command);

                    do {
                        responce = in.readLine();
                        System.out.println(responce);
                    } while (!responce.isEmpty());
                }


            } while (!"exit".equals(command));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {
        String port = null;
        String serverIp = null;

        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();

        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
            port = settings.getValues("server.port");
            serverIp = settings.getValues("server.ip");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try (Socket socket = new Socket(InetAddress.getByName(serverIp), Integer.parseInt(port))) {
            new FileManager(socket).managerStart();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
