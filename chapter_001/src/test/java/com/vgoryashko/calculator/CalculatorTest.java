package com.vgoryashko.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/** 
 * Class that performs testing of a class com.vgoryashko.calculator.Calculator.
 * @author vgoryashko
 * @since 10/10/2016
 * version 1.0
 */

public class CalculatorTest{
	
	@Test
	public void whenAddThenSumOfFirstAndSecond(){
		//Assign
		Calculator calc = new Calculator();
		calc.addition(2.0, 2.0);
		double result = calc.getResult();
		assertThat(result, is(4.0));
	}
	
	@Test
	public void whenSubstractionThenSubstractionOfFirstAndSecond(){
		//Assign
		Calculator calc = new Calculator();
		calc.substraction(12.0, 5.0);
		double result = calc.getResult();
		assertThat(result, is(7.0));
	}
	
	@Test
	public void whenMultiplyThenMultiplicationOfFirstAndSecond(){
		//Assign
		Calculator calc = new Calculator();
		calc.multiplication(6.0, 6.0);
		double result = calc.getResult();
		assertThat(result, is(36.0));
	}
	
	@Test
	public void whenDivisionThenDivisionOfFirstAndSecond(){
		//Assign
		Calculator calc = new Calculator();
		calc.division(27.0, 9.0);
		double result = calc.getResult();
		assertThat(result, is(3.0));
	}
}