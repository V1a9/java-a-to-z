package com.vgoryashko.netfilemanager.server;

import com.vgoryashko.service.Settings;
import java.io.InputStream;
import java.io.IOException;

/**
 * Class that retrieving and stores a server settings.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/2017
 */
public class ServerSettings {

    /**
     * Variable that stores a reference to a file with properties.
     */
    String properties;

    /**
     * Getter for the properties variable.
     */
    public String getProperties() {
        return this.properties;
    }

    /**
     * Constructor for the class.
     *
     * @param aProperties                   a name of a file with a server properties
     */
    public ServerSettings(String aProperties) {
        this.properties = aProperties;
    }

    /**
     * Variable that stores a port value of a server.
     */
    private int port;

    /**
     * Setter for the port variable.
     *
     * @param port                  int value for a given server
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Getter for the port variable.
     *
     * @return                          <code>int<code/>
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Method that retrieves settings for a server.
     */
    public void readProperties() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream(getProperties())) {
            settings.load(in);
            setPort(Integer.parseInt(settings.getValues("server.port")));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
