package com.vgoryashko.checkpalindrome;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 06.02.2017
 */
public class CheckPalindrome {

    /**
     * Method that performs check if a word is a palindrome and prints a result in console.
     */
    public void checkPalindrome() {
        String string;
        Input input = new ConsoleInput();
        Pattern pattern = Pattern.compile("[a-zA-z]{5}");
        Matcher matcher;

        do {
            string = input.input(String.format("Please enter a word from 5 letters or 'y' if you want to exit: "));
            if (!string.equals("y")) {
                matcher = pattern.matcher(string);
                if (matcher.matches()) {
                    System.out.println(String.format("This word is a palindrome: %s%s", string, System.getProperty("line.separator")));
                } else {
                    System.out.println(String.format("This word is NOT a palindrome: %s%s", string, System.getProperty("line.separator")));
                }
            }
        } while (!string.equals("y"));
    }

    /**
     * Starting point for the application.
     *
     * @param args              Standard parameter
     */
    public static void main(String[] args) {
        new CheckPalindrome().checkPalindrome();
    }
}
