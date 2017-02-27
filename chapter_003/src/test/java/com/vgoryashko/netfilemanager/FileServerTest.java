package com.vgoryashko.netfilemanager;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/27/2017
 */
public class FileServerTest {

    @Test
    public void whenFileServer() {
        FileServer server = new FileServer();
        server.serverInit();
        assertThat(server.getPort(), is("4444"));
    }
}