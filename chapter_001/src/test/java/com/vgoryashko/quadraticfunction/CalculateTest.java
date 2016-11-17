package com.vgoryashko.quadraticfunction;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests com.vgoryashko.quadraticfunction.Square.calculate().
 * @author vgoryashko
 * @since 17/10/2016
 * @version 1.0
 */

public class CalculateTest {

	/**
	 * Method that tests calculation of quadratic function.
	 */

	@Test
	public void calculateTest() {
		final int value1 = 2;
		final int value2 = 3;
		final int value3 = -5;
		final float result1 = -5f;
		final float result2 = -6f;
		final float result3 = 9f;
		Square square = new Square(value1, value2, value3);
		assertThat(square.calculate(0), is(result1));
		assertThat(square.calculate(-1), is(result2));
		assertThat(square.calculate(2), is(result3));
	}
}