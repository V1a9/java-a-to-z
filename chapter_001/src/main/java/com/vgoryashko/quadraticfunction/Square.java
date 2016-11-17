package com.vgoryashko.quadraticfunction;

/**
 * Class com.vgoryashko.quadraticfunction.Square that calculates value of qudratic function for a given range of numbers using step.
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.2
 */

public class Square {

	/**
	 * @param a
	 */
	private int a;

	/**
	 * @param b
	 */
	private int b;

	/**
	 * @param c
	 */
	private int c;

	/**
	 * Constructor for the class.
	 * @param aA - integer number
	 * @param bB - integer number
	 * @param cC - integer number
	 */

	Square(int aA, int bB, int cC) {
		this.a = aA;
		this.b = bB;
		this.c = cC;
	}

	/**
	 * Method that calculates a value of quadratic function.
	 * @param x - integer number
	 * @return - float result
	 */

	public float calculate(int x) {
		return (float) (this.a * Math.pow(x, 2) + this.b * x + c);
	}

	/**
	 * Method that prints in console value of qudratic function for a given range of numbers using step.
	 * @param start - a first number in a range
	 * @param finish - a last number in a range
	 * @param step - a number used for defining next number for calculation
	 */

	public void show(int start, int finish, int step) {
		for (int j = start; j <= finish; j += step) {
			if (j < finish) {
				System.out.format("%.1f, ", calculate(j));
			} else {
				System.out.format("%.1f.", calculate(j));
			}
		}
	}
}