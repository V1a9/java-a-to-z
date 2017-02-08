package com.vgoryashko.checkpalindrome;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Class that tests classes that perform check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 06.02.2017
 */
public class CheckPalindromeTest {

    /**
     * Variable that reference to a object of CheckPalindrome class.
     */
    private CheckPalindrome checkPalindrome;

    /**
     * Method that initialise test environments.
     */
    @Before
    public void initSetUp() {
        checkPalindrome = new CheckPalindrome();
    }

    /**
     * Rule for testing exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none().none();

    /**
     * Method that tests.
     */
    @Test
    public void whenTest() {
//        checkPalindrome.checkPalindrome();
    }
}
