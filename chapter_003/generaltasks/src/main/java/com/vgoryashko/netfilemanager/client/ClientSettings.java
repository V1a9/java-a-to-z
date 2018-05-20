package com.vgoryashko.netfilemanager.client;

import com.vgoryashko.service.Settings;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class that retrieving and stores a client settings.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/2017
 */
public class ClientSettings {

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
    public ClientSettings(String aProperties) {
        this.properties = aProperties;
    }

    /**
     * Variable that stores a port value of a server.
     */
    private int port;

    /**
     * Getter for the server IP address variable.
     *
     * @return                          <code>String<code/>
     */
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * Setter for the port variable.
     *
     * @param ipAddress                  String value for an IP of a server
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Variable that stores a server IP address value.
     */
    private String ipAddress;

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

    public void readProperties() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream(getProperties())) {
            settings.load(in);
            setPort(Integer.parseInt(settings.getValues("server.port")));
            setIpAddress(settings.getValues("server.ip"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
