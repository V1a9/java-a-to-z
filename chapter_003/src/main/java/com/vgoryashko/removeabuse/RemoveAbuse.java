package com.vgoryashko.removeabuse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that removes abuse words from input stream.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 12.01.2017
 */
public class RemoveAbuse {

    /**
     * Method that removes abuse words from an input stream and writes a result into output stream.
     *
     * @param input                 Input stream
     * @param out                   Output stream
     * @param abuse                 Array of abuse words to be filtered
     */
    public void dropAbuses(InputStream input, OutputStream out, String[] abuse) {
        String word;
        try (Scanner scanner = new Scanner(input);
            BufferedOutputStream outputStream = new BufferedOutputStream(out)) {
            while (scanner.hasNext()) {
                word = scanner.next();
                int counter = 0;
                for (String s : abuse) {
                    if (word.equals(s)) {
                    } else {
                        if (!word.equals(s) && ++counter == abuse.length) {
                            if (scanner.hasNext()) {
                                outputStream.write(word.getBytes());
                                outputStream.write(32);
                            } else {
                                outputStream.write(word.getBytes());
                            }
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("IOException.");
        }
    }
}
