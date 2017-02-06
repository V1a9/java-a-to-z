package com.vgoryashko.checkpalindrome;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 06.02.2017
 */
public class CheckPalindrome {

    /**
     * Variable that referring to the Input method.
     */
    private Input in;

    /**
     * Constractor for the class.
     * @param aInput            Input method
     */
    public CheckPalindrome(Input aInput) {
        this.in = aInput;
    }

    /**
     * Getter for the in field.
     * @return                  Input
     */
    public Input getIn() {
        return this.in;
    }

    /**
     * Method that performs check if a word is a palindrome and prints a result in console.
     */
    public void checkPalindrome() {
        String string;
        Pattern pattern = Pattern.compile("[a-zA-z]{5}");
        Matcher matcher;

        do {
            string = getIn().input("Please enter a word from 5 letters or 'y' if you want to exit: ");
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
        new CheckPalindrome(new ConsoleInput()).checkPalindrome();
    }
}
