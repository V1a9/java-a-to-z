package com.vgoryashko.netfilemanager.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that implements main logic of the server application for the network file manager.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/6/17
 */
public class Server {

    public Socket getSocket() {
        return this.socket;
    }

    private Socket socket;

    public Server(Socket aSocket) {
        this.socket = aSocket;
    }

    /**
     * Variable that stores the file separator value.
     */
    final String FS = File.separator;

    /**
     * Variable that stores root folder path of the server.
     */
    private final File rootFolder = new File(String.format(".%sroot", FS));

    /**
     * Variable that stores user folder path of the server.
     */
    private final File usrFolder = new File(String.format(".%sroot%susr", FS, FS));

    /**
     * Variable that stores tmp folder path of the server.
     */
    private final File tmpFolder = new File(String.format(".%sroot%stmp", FS, FS));

    /**
     * Variable that stores a path of a currentFolder folder.
     */
    private File currentFolder = rootFolder;

    /**
     * Getter for the variable currentFolderFolder.
     *
     * @return                              <code>File</code>
     */
    public File getCurrentFolder() {
        return currentFolder;
    }

    /**
     * Method that setups the server environments.
     */
    public void initServer() {
        rootFolder.mkdir();
        usrFolder.mkdir();
        tmpFolder.mkdir();
    }

    /**
     * Method that list a content of a currentFolder folder.
     */
    public String[] list(String folderPath) {
        String[] result = null;
        File folder = new File(folderPath);
        if (folder.exists()) {
            result = folder.list();
        }
        return result;
    }

    /**
     * Method that implements navigation through the file server.
     */
    public void changeDir(String folderPath) {
        currentFolder = new File(folderPath);
    }

    public void start() {

        try (PrintWriter out =
                     new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connection is established.");
            out.println("You're connected to the file server.");

            String clientCommand;
            File dwnFile = null;
            File uplFile;
            boolean uplReady;
            boolean dwnReady = false;

            do {

                out.println();
                out.println(String.format("Enter command: "));
                clientCommand = in.readLine();

                if ("exit".equals(clientCommand)) {

                    out.println("Server is shutting down...");
                    out.println();

                } else if ("ls".equals(clientCommand)) {

                    File[] files = currentFolder.listFiles();

                    if (files.length != 0) {

                        out.println(String.format("%s%s", currentFolder, FS));

                        for (File file : files) {
                            if (file.isDirectory()) {
                                out.println(String.format("%s%s", file.toString(), FS));
                            } else {
                                out.println(String.format("%s", file.toString()));
                            }
                        }

                        out.println();

                    } else {
                        out.println(String.format("%s", currentFolder, FS));
                        out.println();
                    }

                } else if ("pwd".equals(clientCommand)) {

                    out.println(String.format("%s%s", currentFolder, FS));
                    out.println();

                } else if (clientCommand.startsWith("cd ")) {

                    String dir = clientCommand.substring("cd ".length(), clientCommand.length());
                    String[] files = currentFolder.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                            if (dir.equals(files[index])) {
                                currentFolder = new File(String.format("%s%s%s", rootFolder, FS, dir));
                                out.println(String.format("%s%s", currentFolder, FS));
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

                    currentFolder = new File(currentFolder.toString().substring(0, rootFolder.toString().length()));
                    out.println(String.format("%s%s", currentFolder, FS));
                    out.println();
                } else if (clientCommand.startsWith("upl ")) {

                    /**
                     * Setup socket prior client setup.
                     */
                    String fileName = clientCommand.substring("upl ".length(), clientCommand.length());
                    String[] files = currentFolder.list();

                    if (files.length > 0) {
                        for (int index = 0; index < files.length; index++) {
                            if (fileName.equals(files[index])) {
                                out.println("A file with such name already exists.");
                                out.println();
                            } else if (!fileName.equals(files[index]) && index == files.length - 1) {
                                dwnFile = new File(String.format("%s%s%s", currentFolder.toString(), FS, fileName));

                                out.println(String.format("%s%s%s", currentFolder.toString(), FS, fileName));
                                out.println();

                                dwnReady = true;

                            }
                        }
                    } else {

                        dwnFile = new File(String.format("%s%s%s", currentFolder.toString(), FS, fileName));
                        out.println(String.format("%s%s%s", currentFolder.toString(), FS, fileName));
                        out.println();
                    }
                }

                if (dwnReady) {

                    try (BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                         BufferedWriter writer = new BufferedWriter(new FileWriter(dwnFile))) {

                        String data;
                        do {
                            if ((data = input.readLine()) != null); {
                                writer.write(data);
                            }
                        } while (data != null);
                    }
                }
            } while (!("exit".equals(clientCommand)));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ServerSettings serverSettings = new ServerSettings("app.properties");
        serverSettings.readProperties();

        try (ServerSocket serverSocket = new ServerSocket(serverSettings.getPort());
             Socket socket = serverSocket.accept()) {

            Server server = new Server(socket);
            server.initServer();
            server.start();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + serverSettings.getPort() + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
