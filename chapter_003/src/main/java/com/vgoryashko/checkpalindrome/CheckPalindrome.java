package com.vgoryashko.checkpalindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 08.02.2017
 */
public class CheckPalindrome {

    /**
     * Starting point for the application.
     *
     * @param args              Standard parameter for the method main()
     */
    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {

                System.out.print("Enter a word 5 letters length, or 'q' to exit: ");
                String string = bufferedReader.readLine();
                String stringToLowerCase = string.toLowerCase();

                    if ("q".equals(string.toLowerCase())) {
                        System.out.println("Exit!");
                        System.exit(0);
                    }
                    if (string.length() != 5) {
                        System.out.printf("This word \"%s\" is not 5 letters long, please try again.".concat(System.getProperty("line.separator")), string);
                    } else if (string.toLowerCase().equals(new StringBuilder(stringToLowerCase).reverse().toString())) {
                        System.out.printf("This word \"%s\" is a palindrome.".concat(System.getProperty("line.separator")), string);
                    } else {
                        System.out.printf("This word \"%s\" is not a palindrome.".concat(System.getProperty("line.separator")), string);

                    }

                System.out.println("-----------".concat(System.getProperty("line.separator")));

                }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
