package com.vgoryashko.interactivecalc;

 /**
 * Class that performs operation "+", "-", "*", "/" with two operands.
 * @author vgoryashko
 * @version 0.2
 * @since 5/19/17
 */

public class Calculator {
	/**
	 * @param result variable of the type double to store a result
	 */
	private double result;

	/**
	 * Addition.
	 * @param first - first argument
	 * @param second - second argument
	 */

	public void addition(double first, double second) {
		result = first + second;
	}

	/**
	 * Substraction.
	 * @param first - first argument
	 * @param second - second argument
	 */

	public void substraction(double first, double second) {
		result = first - second;
	}

	/**
	 * Multiplication.
	 * @param first - first argument
	 * @param second - second argument
	 */

	public void multiplication(double first, double second) {
		result = first * second;
	}

	/**
	 * Division.
	 * @param first - first argument
	 * @param second - second argument
	 */
	public void division(double first, double second) {
		result = first / second;
	}

	/**
	 * Method getter.
	 * @return result
	 */
	public double getResult() {
		return result;
	}
}