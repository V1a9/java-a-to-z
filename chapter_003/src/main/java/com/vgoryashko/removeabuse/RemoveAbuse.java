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
 * @version 0.2
 * @since 11.01.2017
 */
public class RemoveAbuse {

    public void dropAbuses(InputStream input, OutputStream out, String[] abuse) {
        String word;
        try (Scanner scanner = new Scanner(input);
            BufferedOutputStream outputStream = new BufferedOutputStream(out)) {
            while (scanner.hasNext()) {
                word = scanner.next();
                int counter = 0;
                for (String s : abuse) {
                    if (word.equals(s)) {
                        continue;
                    } else if (!word.equals(s) && ++counter == abuse.length) {
                        outputStream.write(word.getBytes());
                    }
                }
            }
        } catch (IllegalStateException ise) {
            System.out.println("Input stream is closed.");
        } catch (NoSuchElementException nsee) {
            System.out.println("There is no data in the stream.");
        } catch (IOException ioe) {
            System.out.println("IOException.");
        }
    }
}
