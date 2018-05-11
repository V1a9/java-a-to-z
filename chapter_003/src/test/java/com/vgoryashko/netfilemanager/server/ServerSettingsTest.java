package com.vgoryashko.netfilemanager.server;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/2017
 */
public class ServerSettingsTest {

    /**
     * Method that tests readProperties() method.
     *
     * @throws Exception                    Exception
     */
    @Test
    public void testReadProperties() throws Exception {
        ServerSettings serverSettings = new ServerSettings("app.properties");
        serverSettings.readProperties();
        assertThat(serverSettings.getPort(), is(4444));
    }
}