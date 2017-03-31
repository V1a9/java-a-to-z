package com.vgoryashko.searchfile;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by vgoryash on 29.03.2017.
 */
public class SearchFileTest {

    @Test
    public void rootTest() {

        Path root = Paths.get("C:\\local_installation_source");

        try {

            Stream stream = Files.walk(root, Integer.MAX_VALUE);
            Object[] list = stream.toArray();
            System.out.println(list.length);
            System.out.println(Arrays.toString(list));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
