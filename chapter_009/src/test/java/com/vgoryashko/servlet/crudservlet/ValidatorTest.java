package com.vgoryashko.servlet.crudservlet;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class that tests Validator class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/27/17
 */
public class ValidatorTest {

    @Test
    public void whenAllInputsAreEmptyThenAllResultsFalse() throws Exception {

        boolean[] expected = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
        };

        String[] input = new String[]{
          "",
          "",
          "",
          "",
          "",
          "",
          "",
          "",
        };

        int i = 0;
        for (boolean result : new Validator(input).validate()) {
            assertTrue(result == expected[i++]);
        }

    }

    @Test
    public void whenNameInputValidThenResultZeroTrue() throws Exception {

        String[] input = new String[]{
                "John Dow",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
        };

        assertTrue(new Validator(input).validate()[0]);
    }
}