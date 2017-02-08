package com.vgoryashko.checkpalindrome;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 08.02.2017
 */
public class CheckPalindrome {

    /**
     * Method that reads words from the console and validate entered words.
     *
     * @return String                   a word to be checked
     * @throws RuntimeException         RuntimeException
     */
    public String input() throws RuntimeException {
        String result = null;
        try (Scanner scanner = new Scanner(System.in)) {
            result = scanner.nextLine();
        } catch (NoSuchElementException nsee) {
            System.out.println("IOException in input() method.");
        }
        return result;
    }

    /**
     * Starting point for the application.
     *
     * @param args              Standard parameter for the method main()
     */
    public static void main(String[] args) {
        CheckPalindrome checkPalindrome = new CheckPalindrome();
        String string = null;
        String reverseString;
        StringBuilder stringBuilder;

        do {
            System.out.println("Please enter a word from 5 letters or 'y' if you want to exit: ");
            string = checkPalindrome.input().toLowerCase();
            if (!string.equals("y")) {
                if (string.length() == 5) {
                    stringBuilder = new StringBuilder(string);
                    reverseString = stringBuilder.reverse().toString();
                    if (string.equals(reverseString)) {
                        System.out.printf("This word \"%s\" is a palindrome.".concat(System.getProperty("line.separator")), string);
                    } else {
                        System.out.printf("This word \"%s\" is not a palindrome.".concat(System.getProperty("line.separator")), string);
                    }
                } else {
                    System.out.printf("This word \"%s\" is not 5 letters long, please try again.".concat(System.getProperty("line.separator")), string);
                }
            }
        } while (string != "y");
    }
}
