package com.vgoryashko.interactivecalc;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests implementation of the InteractCalc application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/19/17
 */
public class InteractCalcTest {

    /**
     * Variable that stores line separator value.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Test that checks operation 12 + 13 and printing of the expected program messages flow.
     */
    @Test
    public void whenAdditionTestedThenCorrectResultAndProgramFlowIsDepicted() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(Joiner.on(NL)
                .join(
                        "1",
                        "12",
                        "13",
                        "7"
                ).getBytes()
        );

        System.setIn(inputStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));


        String expected = Joiner.on("\n")
                .join("1. Add;\r",
                        "2. Substract;\r",
                        "3. Multiply;\r",
                        "4. Divide;\r",
                        "5. Perform previous operation;\r",
                        "6. Calculation with previous result;\r",
                        "7. Exit.\r",
                        "Chose operation: ",
                        "Enter 1st operand: ",
                        "Enter 2nd operand: ",
                        "Result is: 25,000000\r",
                        "1. Add;\r",
                        "2. Substract;\r",
                        "3. Multiply;\r",
                        "4. Divide;\r",
                        "5. Perform previous operation;\r",
                        "6. Calculation with previous result;\r",
                        "7. Exit.\r",
                        "Chose operation: ",
                        "Exiting...\r",
                        ""
                );

        new InteractCalc(new Calculator(), new ConsoleInput(new Scanner(System.in))).start();
        assertThat(out.toString(), is(expected));
    }
}
