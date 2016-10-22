package com.vgoryashko.factorial;

/* 
 * Class that tests class com.vgoryashko.factorial.Factorial
 * @author vgoryashko
 * @since 19/10/2016
 * @version 1.0
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class FactorialTest{

	@Test
	public void whenIntIsEnteredThenItsFactorialCalculated (){
		Factorial factorial = new Factorial();
		assertThat(factorial.calcFactorial(10), is(3628800));
		assertThat(factorial.calcFactorial(5), is(120));
	}
}