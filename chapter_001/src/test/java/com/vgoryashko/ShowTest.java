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
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

public class ShowTest{

	@Test
	public void showTest(){
		Square square = new Square(2, 3, -5);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		square.show(-3, 2, 1);
		assertThat(out.toString(), is("[4.0, -3.0, -6.0, -5.0, 0.0, 9.0]")); //"[4, -3, -6, -6.125, -5, 0, 9]"
	}
}