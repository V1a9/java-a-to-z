package com.vgoryashko.checkpalindrome;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
     * Method that tests .
     */
    @Test
    public void whenCorrectWordEnteredThenRespectiveResultReceived() {
        assertTrue(checkPalindrome.checkPalindrome("civic"));
        assertTrue(checkPalindrome.checkPalindrome("ToPot"));
        assertTrue(checkPalindrome.checkPalindrome("POTOP"));
        assertFalse(checkPalindrome.checkPalindrome("FOTOP"));
        assertFalse(checkPalindrome.checkPalindrome("asdfg"));
    }
}
