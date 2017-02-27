package com.vgoryashko.netfilemanager;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/27/2017
 */
public class FileServerTest {

    @Test
    public void whenRootFolder() {
        File root = new File("./");
        String[] files = root.list();

        for (String s : files) {
            System.out.println(s);
        }
    }
}