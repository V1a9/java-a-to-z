package com.vgoryashko.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that works.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/24/2017
 */
public class Settings {

    private final Properties prs = new Properties();

    public void load(InputStream io) {
        try {
            this.prs.load(io);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
    }

    public String getValues(String key) {
        return this.prs.getProperty(key);
    }
}
