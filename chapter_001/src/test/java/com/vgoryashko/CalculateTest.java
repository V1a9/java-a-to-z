package com.vgoryashko;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest{
	//Special annotation to depict that this is test
	@Test
	public void whenSetStopInEchoThenReturnThreeStops (){

		//Assign block
		Calculate calc = new Calculate();
		String result = calc.echo("stop");
		//Static method from library hamcrest which extend Junit library
		/*Assert.*/assertThat(result, is("stop stop stop"));
	}
	
	@Test
	public void whenSetNullInEchoThenReturnTwoSpaces (){
		Calculate calc = new Calculate();
		String result = calc.echo(null);
		assertThat(result, is("null null null"));
	}
	
}