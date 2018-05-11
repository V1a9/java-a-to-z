package com.vgoryashko.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that performs testing of a class com.vgoryashko.calculator.Calculator.
 * @author vgoryashko
 * @since 10/10/2016
 * version 1.0
 */

public class CalculatorTest {

	/**
	 * Class that test addition.
	 */

	@Test
	public void whenAddThenSumOfFirstAndSecond() {
		//Assign
		final double value1 = 2.0;
		final double value2 = 2.0;
		final double res = 4.0;
		Calculator calc = new Calculator();
		calc.addition(value1, value2);
		double result = calc.getResult();
		assertThat(result, is(res));
	}

	/**
	 * Class that test substraction.
	 */

	@Test
	public void whenSubstractionThenSubstractionOfFirstAndSecond() {
		//Assign
		final double value1 = 12.0;
		final double value2 = 5.0;
		final double res = 7.0;
		Calculator calc = new Calculator();
		calc.substraction(value1, value2);
		double result = calc.getResult();
		assertThat(result, is(res));
	}

	/**
	 * Class that test multiplication.
	 */

	@Test
	public void whenMultiplyThenMultiplicationOfFirstAndSecond() {
		//Assign
		final double value1 = 6.0;
		final double value2 = 6.0;
		final double res = 36.0;
		Calculator calc = new Calculator();
		calc.multiplication(value1, value2);
		double result = calc.getResult();
		assertThat(result, is(res));
	}

	/**
	 * Class that test division.
	 */

	@Test
	public void whenDivisionThenDivisionOfFirstAndSecond() {
		//Assign
		final double value1 = 27.0;
		final double value2 = 9.0;
		final double res = 3.0;
		Calculator calc = new Calculator();
		calc.division(value1, value2);
		double result = calc.getResult();
		assertThat(result, is(res));
	}
}