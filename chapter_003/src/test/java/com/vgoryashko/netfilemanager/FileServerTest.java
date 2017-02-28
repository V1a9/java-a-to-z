package com.vgoryashko.netfilemanager;

import org.junit.Test;

import java.io.File;

/**
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/27/2017
 */
public class FileServerTest {

    /**
     * Test for defining a root folder.
     */
    @Test
    public void whenRootFolder() {
        File root = new File("./");
        String[] files = root.list();

        for (String s : files) {
            System.out.println(s);
        }
    }
}