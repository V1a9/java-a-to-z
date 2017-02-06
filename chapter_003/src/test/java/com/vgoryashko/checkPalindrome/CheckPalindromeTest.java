package com.vgoryashko.checkPalindrome;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

/**
 * Created by Vlad Goryashko on 2/6/2017.
 */
public class CheckPalindromeTest {

    private CheckPalindrome checkPalindrome;

    @Before
    public void initSetUp() {
        checkPalindrome = new CheckPalindrome();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none().none();

    @Test
    public void whenTest() throws RuntimeException, IOException {
        checkPalindrome.main(null);
    }
}
