package com.vgoryashko.netfilemanager;

import com.vgoryashko.service.Settings;

import java.io.InputStream;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/27/2017
 */
public class FileServer {

    /**
     * Variable that stores a port value.
     */
    private String port;

    /**
     * Method setter for a port variable.
     */
    public void setPort(String aPort) {
        this.port = aPort;
    }

    /**
     * Method getter for a port variable.
     */
    public String getPort() {
        return this.port;
    }

    /**
     * Method that initialize a server.
     */
    public void serverInit() {

        String port = null;

        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();

        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
            setPort(settings.getValues("server.port"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method entry point for the application.
     * @param args                      standard argument for the main method
     */
    public static void main(String[] args) {
        FileServer server = new FileServer();
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(server.getPort()))) {

            serverSocket.accept();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
