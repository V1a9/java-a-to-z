package com.vgoryashko;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest{
	@Test
	public void whenAddthenSumOfFirstAndSecond(){
		//Assign
		Calculator calc = new Calculator();
		calc.add(2.0, 2.0);
		double result = calc.getResult();
		assertThat(result, is(4.0));
	}
	
}