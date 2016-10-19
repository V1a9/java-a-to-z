package com.vgoryashko;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class FactorialTest{

	@Test
	public void whenIntIsEnteredThenItsFactorialCalculated (){
		assertThat(new Factorial().calcFactorial(10), is(3628800));
		assertThat(new Factorial().calcFactorial(5), is(120));
	}
}