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
     * Variable that stores a path of a current folder.
     */
    private File currentFolder = rootFolder;

    /**
     * Getter for the variable currentFolder.
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
     * Method that list a content of a current folder.
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
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
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
