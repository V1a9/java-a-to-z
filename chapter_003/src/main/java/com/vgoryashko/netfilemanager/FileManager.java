package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class that implements a network file server.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/28/2017
 */
public class FileManager {

    /**
     * Variable that referring to an instance of a Socket class.
     */
    private Socket socket;

    /**
     * Constructor for the class.
     *
     * @param aSocket                   Socket for the server
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
                System.out.println(in.readLine());
                command = scanner.nextLine();

                if ("exit".equals(command)) {

                    out.println(command);
                    responce = in.readLine();
                    System.out.println(responce);

                } else if (command.startsWith("upload ")) {

                    String filePath = command.substring("upload ".length(), command.length());
                    File outFile = new File(filePath);

                    String fileName = outFile.getName();

                    out.println(String.format("upload %s", fileName));

                    do {
                        responce = in.readLine();
                        if (!responce.isEmpty()) {
                            System.out.println(responce);
                        }
                    } while (!responce.isEmpty());

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
