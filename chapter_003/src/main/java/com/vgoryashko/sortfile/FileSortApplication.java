package com.vgoryashko.sortfile;

import java.io.*;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 17.01.2017
 */
public class FileSortApplication {

    public void sort(File source, File dest) throws IOException {

        if(!source.exists()) {
            throw new FileNotFoundException("There is no such resource file.");
        } else {
            try {
                RandomAccessFile sourceFile = new RandomAccessFile(source, "r");
                FileWriter destFile = new FileWriter(dest, true);

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
