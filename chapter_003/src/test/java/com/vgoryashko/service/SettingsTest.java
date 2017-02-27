package com.vgoryashko.service;

import org.junit.Test;

import java.io.File;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/27/2017
 */
public class SettingsTest {

    /**
     * Test that checks correctness of reading properties from app.properties file.
     * @throws Exception                    Exception
     */
    @Test
    public void whenClassLoader() throws Exception {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("app.properties")) {
            settings.load(inputStream);
        }
        String value = settings.getValues("server.port");
        assertThat(value, is("4444"));
        value = settings.getValues("server.ip");
        assertThat(value, is("127.0.0.1"));

    }
}