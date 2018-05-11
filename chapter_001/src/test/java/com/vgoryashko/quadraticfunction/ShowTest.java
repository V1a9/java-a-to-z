package com.vgoryashko.quadraticfunction;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class that tests com.vgoryashko.quadraticfunction.Square.show().
 * @author vgoryashko
 * @since 19/10/2016
 * @version 1.1
 */

public class ShowTest {

	/**
	 * Method that tests printing a result of quadratic function calculation into console.
	 */

	@Test
	public void showTest() {
		final int value0 = 1;
		final int value1 = 2;
		final int value2 = 3;
		final int value3 = -5;
		final int value4 = -3;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Square square = new Square(value1, value2, value3);
		System.setOut(new PrintStream(out));
		square.show(value4, value1, value0);
		assertThat(out.toString(), is("4.0, -3.0, -6.0, -5.0, 0.0, 9.0."));
	}
}