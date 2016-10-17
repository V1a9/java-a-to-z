package com.vgoryashko;

/* 
 * Class that tests com.vgoryashko.Square.calculate()
 * @author vgoryashko
 * @since 17/10/2016
 * @version 1.0
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CalculateTest{

	@Test
	public void calculateTest(){
		Square square = new Square(2, 3, -5);
		assertThat(square.calculate(0), is(-5f));
		assertThat(square.calculate(-1), is(-6f));
		assertThat(square.calculate(2), is(9f));
	}
}