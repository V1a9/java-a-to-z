package com.vgoryashko;

/* 
 * Class that tests com.vgoryashko.Square.show()
 * @author vgoryashko
 * @since 17/10/2016
 * @version 1.0
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ShowTest{

	@Test
	public void showTest(){
		Square square = new Square(2, 3, -5);
		assertArrayEquals(square.show(-3, 2, 1), is(new float[]{4, -3, -6, -6.125, -5, 0, 9}));
	}
}