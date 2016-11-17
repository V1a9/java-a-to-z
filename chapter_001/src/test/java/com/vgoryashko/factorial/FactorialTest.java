package com.vgoryashko.factorial;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests class com.vgoryashko.factorial.Factorial.
 * @author vgoryashko
 * @since 19/10/2016
 * @version 1.0
 */

public class FactorialTest {

	/**
	 * Method that tests calculation of factorial.
	 */

	@Test
	public void whenIntIsEnteredThenItsFactorialCalculated() {
		final int value1 = 10;
		final int value2 = 5;
		final int result1 = 3628800;
		final int result2 = 120;
		Factorial factorial = new Factorial();
		assertThat(factorial.calcFactorial(value1), is(result1));
		assertThat(factorial.calcFactorial(value2), is(result2));
	}
}