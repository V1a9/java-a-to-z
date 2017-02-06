package com.vgoryashko.checkPalindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 06.02.2017
 */
public class CheckPalindrome {

    public static void main(String[] args) throws RuntimeException {
        String string;

        try {
//            BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
            Scanner inputStream = new Scanner(System.in);
            do {
                System.out.println(String.format("Please enter a word from 5 letters or 'y' if you want to exit: "));
//                string = inputStream.readLine();
//                if (!string.equals("y")) {
//                    if (string.length() == 5) {
//                        Check check = new Check(string);
//                        if (check.checkPalindrome()) {
//                            System.out.println(String.format("This word is a palindrome: %s", string));
//                        } else {
//                            System.out.println(String.format("This word is NOT a palindrome: %s", string));
//                        }
//                    } else {
//                        throw new InvalidWordException();
//                    }
//                }
                string = inputStream.nextLine();
            } while (!string.equals("y"));
        } catch (NoSuchElementException nsee) {
            throw new RuntimeException("RuntimeException in the main method.");
        } catch (InvalidWordException iwe) {
            System.out.println("Entered word consists less than 5 letters, try again.");
        }
    }
}
