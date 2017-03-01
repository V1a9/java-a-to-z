package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * @author Vlad Goryashko
 * @version 0.6
 * @since 3/01/2017
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
        File dwnFile = null;
        File uplFile = null;
        boolean uplReady = false;
        boolean dwnReady = false;

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

                out.println();
                out.println(String.format("Enter command: "));
                clientCommand = in.readLine();

                if ("exit".equals(clientCommand)) {

                    out.println("Server is shutting down...");
                    out.println();

                } else if ("ls".equals(clientCommand)) {

                    File[] files = current.listFiles();

                    if (files.length != 0) {

                        out.println(String.format("%s%s", current, FS));

                        for (File file : files) {
                            if (file.isDirectory()) {
                                out.println(String.format("%s%s", file.toString(), FS));
                            } else {
                                out.println(String.format("%s", file.toString()));
                            }
                        }

                        out.println();

                    } else {
                        out.println(String.format("%s", current));
                        out.println();
                    }

                } else if ("pwd".equals(clientCommand)) {

                    out.println(String.format("%s%s", current, FS));
                    out.println();

                } else if (clientCommand.startsWith("cd ")) {

                    String dir = clientCommand.substring("cd ".length(), clientCommand.length());
                    String[] files = current.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                            if (dir.equals(files[index])) {
                                current = new File(String.format("%s%s%s", rootFolder, FS, dir));
                                out.println(String.format("%s%s", current, FS));
                                out.println();
                            } else if (index == files.length) {
                                out.println("There is no such directory.");
                                out.println();
                            }
                        }
                    } else {
                        out.println("There is no such directory.");
                        out.println();
                    }

                } else if ("..".equals(clientCommand)) {

                    current = new File(current.toString().substring(0, rootFolder.toString().length()));
                    out.println(String.format("%s%s", current, FS));
                    out.println();

                } else if (clientCommand.startsWith("upl ")) {

                    String fileName = clientCommand.substring("upl ".length(), clientCommand.length());
                    String[] files = current.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                           if (fileName.equals(files[index])) {
                               out.println("A file with such name already exists.");
                               out.println();
                           } else if (!fileName.equals(files[index]) && index == files.length - 1) {
                               dwnFile = new File(String.format("%s%s%s", current.toString(), FS, fileName));

                               out.println(String.format("%s%s%s", current.toString(), FS, fileName));
                               out.println();

                               dwnReady = true;

                           }
                        }
                    } else {
                        dwnFile = new File(String.format("%s%s%s", current.toString(), FS, fileName));
                        out.println(String.format("%s%s%s", current.toString(), FS, fileName));
                        out.println();

                    }
                }

                /**
                 * Check why -1 isn't returned.
                 */
                if (dwnReady) {

                    try (BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                         RandomAccessFile download = new RandomAccessFile(dwnFile, "rw")) {

                        int data;
                        do {
                            data = input.read();
                            if (data != -1) {
                                download.write(data);
                            }
                        } while (data != -1);
                    }
                }

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
