package com.vgoryashko.searchfile;

import org.junit.Test;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by vgoryash on 29.03.2017.
 */
public class SearchFileTest {

    @Test
    public void rootTest() {

        File[] roots = File.listRoots();
        System.out.println("Root directories in your system are:");

        for (int i = 0; i < roots.length; i++) {
            System.out.println(roots[i].toString());
        }
    }
}
