package com.vgoryashko.checkbytestream;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that checks input stream for even integer.
 * @author Vlad Goryashko
 * @version 2.0
 * @since 11.01.2017
 */

public class CheckByteStreamForEvenNumber {

    /**
     * Method that performs checking of input stream of bytes for even integer.
     *
     * @param Input                     input stream od data
     * @return boolean                  true if even number, false if - odd
     */
    public boolean isNumber(InputStream Input) {
        boolean even = false;
        try (Scanner scanner = new Scanner(Input)) {
            int number = scanner.nextInt();
            if (number != 0 && (number % 2 == 0) && (!scanner.hasNextInt())) {
                even = true;
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            return even;
        }
        return even;
    }
}