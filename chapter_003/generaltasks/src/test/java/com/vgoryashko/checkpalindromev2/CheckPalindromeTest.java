package com.vgoryashko.checkpalindromev2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

/**
 * Class that tests classes that perform check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/15/17
 */
public class CheckPalindromeTest {

    /**
     * For testing of exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Method that tests checkPalindrome() method - expected result is true.
     */
    @Test
    public void whenCheckPalindromeIvokedThenRespectiveResultReceived() {
        assertTrue(new CheckPalindrome("civic").checkPalindrome());
        assertTrue(new CheckPalindrome("PotOp").checkPalindrome());
    }

    /**
     * Method tests either a InvalidNumberOfLettersException is thrown.
     */
    @Test
    public void whenWrongWordEnteredThenException() {
        expectedException.expect(RuntimeException.class);
        new CheckPalindrome("ciic").checkPalindrome();
        throw new InvalidNumberOfLettersException("Wrong qty of letters.");
    }
}
