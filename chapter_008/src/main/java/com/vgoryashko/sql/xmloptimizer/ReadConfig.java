package com.vgoryashko.sql.xmloptimizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 11/2/17
 */
public class ReadConfig {

    private final Properties properties = new Properties();

    public void load(InputStream inputStream) {
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

}
