package com.vgoryashko.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that works with *.properties files.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/27/2017
 */
public class Settings {

    /**
     * Variable that referring to an instance of Properties class which represents
     * set of properties used in an application.
     */
    private final Properties prs = new Properties();

    /**
     * Method that reads a property from a stream.
     * @param io                        Input stream
     */
    public void load(InputStream io) {
        try {
            this.prs.load(io);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    /**
     * Method that read a property based on a key.
     * @param key                       a key associated with a property
     * @return String                   a property
     */
    public String getValues(String key) {
        return this.prs.getProperty(key);
    }
}
