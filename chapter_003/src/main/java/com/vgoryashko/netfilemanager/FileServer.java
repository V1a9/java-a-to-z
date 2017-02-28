package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.File;

/**
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/27/2017
 */
public class FileServer {

    /**
     * Variable that referring to an instance of a Socket class.
     */
    private Socket socket;

    /**
     * Constructor for the class.
     *
     * @param aSocket                   Socket to be used for the client
     */
    public FileServer(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Method that implements basic logic of the file server application.
     */
    public void serverStart() {
        
        final String FS = File.separator;

        String clientCommand = null;

        File rootFolder = new File(String.format(".%sroot", FS));
        File usrFolder = new File(String.format(".%sroot%susr", FS, FS));
        File tmpFolder = new File(String.format(".%sroot%stmp", FS, FS));

        rootFolder.mkdir();
        usrFolder.mkdir();
        tmpFolder.mkdir();

        File current = rootFolder;

        System.out.println("Server is up.");
        System.out.println("Waiting for a connection...");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {

            System.out.println("Connection is established.");
            out.println("You're connected to the file server.");

            do {

                out.println(String.format("%s# ", current));
                clientCommand = in.readLine();

                if ("exit".equals(clientCommand)) {
                    out.println("Server is shutting down...");
                    out.println();
                } else if ("ls".equals(clientCommand)) {
                    String[] files = current.list();
                    if (files.length != 0) {
                        out.println(String.format("%s", current));
                        for (String element : files) {
                            out.println(String.format("%s%s", element, FS));
                        }
                        out.println();
                    } else {
                        out.println(String.format("%s", current));
                        out.println();
                    }
                } else if ("pwd".equals(clientCommand)) {
                    out.println(String.format("%s%s", current, FS));
                    out.println();
                }
//                } else if (String.format("cd .%s%s%s", rootFolder, FS, clientCommand).equals(clientCommand)) {
//                    current = new File(String.format("cd .%s%s%s", "cd", rootFolder, FS, clientCommand));
//                    out.println();
//                } else if ("mkdir".equals(clientCommand)) {
//
//                }

            } while (!("exit".equals(clientCommand)));

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

        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();

        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
            port = settings.getValues("server.port");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try (Socket serverSocket = new ServerSocket(Integer.parseInt(port)).accept()) {
            new FileServer(serverSocket).serverStart();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
