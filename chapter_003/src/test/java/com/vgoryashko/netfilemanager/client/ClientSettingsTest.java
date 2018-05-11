package com.vgoryashko.netfilemanager.client;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/2017
 */
public class ClientSettingsTest {

    @Test
    public void testReadProperties() throws Exception {
        ClientSettings clientSettings = new ClientSettings("app.properties");
        clientSettings.readProperties();
        assertThat(clientSettings.getPort(), is(4444));
        assertThat(clientSettings.getIpAddress(), is("127.0.0.1"));
    }
}