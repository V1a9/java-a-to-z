package com.vgoryashko.checkpalindrome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests classes that perform check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 11.02.2017
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
     * Method that tests .
     */
    @Test
    public void whenCorrectWordEnteredThenRespectiveResultReceived() {
        assertTrue(checkPalindrome.checkPalindrome(new char[]{'c', 'i', 'v', 'i', 'c'}));
        assertFalse(checkPalindrome.checkPalindrome(new char[]{'b', 'i', 'v', 'i', 'c'}));
        assertTrue(checkPalindrome.checkPalindrome(new char[]{'T', 'o', 'P', 'O', 't'}));
    }
}
