package com.vgoryashko.trianglearea;

/**
 * Class that calculates max length of a shape's side.
 * @author vgoryashko
 * @since 15/10/2016
 * version 1.1
 */

public class CalcLongestSide {

	/**
	* Method that calculates max length of a shape's side.
	* @param args - array of doubles (varargs)
	* @return maxLength
	*/

	public double calcLongestSide(double... args) {
		double maxLength = 0;
		for (double length : args) {
			if (length >= maxLength) {
				maxLength = length;
			}
		}
		return maxLength;
	}
}