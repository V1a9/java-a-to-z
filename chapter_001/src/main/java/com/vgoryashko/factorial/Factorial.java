package com.vgoryashko;

public class Factorial{

	public int calcFactorial (int number){

		int result;
	
		if (number == 1)
			result = 1;
			result = number * (calcFactorial(number - 1));
		return result;
	}
}