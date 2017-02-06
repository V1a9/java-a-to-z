package com.vgoryashko.checkpalindrome;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

/**
 * Class that tests classes that perform check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.2
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
     * @throws IOException                          IOException
     */
    @Test
    public void whenTest() throws IOException {
        checkPalindrome.checkPalindrome();
    }
}
