package com.vgoryashko.service;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/24/2017
 */
public class SettingsTest {

    @Test
    public void whenLoadThenGetFile() throws IOException {
        Settings settings = new Settings();
        File appSettings = new File(String.format("..%sapp.properties", File.separator));
        try (FileInputStream inputStream = new FileInputStream(appSettings)) {
            settings.load(inputStream);
        }
        String value = settings.getValues("home.path");
        assertThat(value, is("c:\\temp\\"));
    }

    @Test
    public void RelativeTest() {
        File root = new File(String.format("..%s", File.separator));
        String[] list = root.list();
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test
    public void whenClassLoader() throws Exception {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("app.properties")) {
            settings.load(inputStream);
        }
    }

    @Test
    public void whenReadSystemProperties() {
        
    }
}