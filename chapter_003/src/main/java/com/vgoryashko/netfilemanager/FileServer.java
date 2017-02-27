package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/27/2017
 */
public class FileServer {

    /**
     * Variable that referring to an instance of a Socket class.
     */
    private Socket socket;

    /**
     * Constructor for the class.
     */
    public FileServer(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Method that implements basic logic of the file server application.
     */
    public void serverStart() {

        String clientCommand = null;

        File rootFolder = new File(String.format(".%sroot", File.separator));

        File current = rootFolder;

//        if (rootFolder.exists()) {
//            rootFolder.delete();
//        }

        rootFolder.mkdir();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {

            out.println("You're connected to the file server.");

            do {

                out.println("Enter a command or \"exit\" to shutdown the server: ");
                clientCommand = in.readLine();

                if ("exit".equals(clientCommand)) {

                } else if ("show".equals(clientCommand)) {
                    String[] files = current.list();
                    if (files.length != 0) {
                        out.println(String.format("%s", rootFolder));
                        for (String element : files) {
                            out.println(element);
                        }
                        out.println("");
                    } else {
                        out.println(String.format("%s", rootFolder));
                        out.println();
                    }
                } else if ("mk".equals(clientCommand)) {
                    out.println();
                    out.println("Please enter file's name: ");
                    out.println();
                    clientCommand = in.readLine();
                    new File(String.format("%s%s%s", current, File.separator, clientCommand)).createNewFile();
                    out.println(String.format("File %s%s%s has been created.", current, File.separator, clientCommand));
                    out.println();
                } else if ("mkdir".equals(clientCommand)) {
                    out.println();
                    out.println("Please enter dir's name: ");
                    out.println();
                    clientCommand = in.readLine();
                    new File(String.format("%s%s%s", current, File.separator, clientCommand)).mkdir();
                    out.println(String.format("File %s%s%s has been created.", current, File.separator, clientCommand));
                    out.println();
                }

            } while (!"exit".equals(clientCommand));

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
