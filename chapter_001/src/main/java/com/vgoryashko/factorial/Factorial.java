package com.vgoryashko;

/* 
 * Class that calculates factorial of a number
 * @author vgoryashko
 * @since 19/10/2016
 * @version 1.0
 */

public class Factorial{

	public int calcFactorial (int number){

		int result = 0;
	
		if (number == 1) {
			result = 1;
		} else if (number > 1) {
			result = number * (calcFactorial(number - 1));
		}
		return result;
	}
}