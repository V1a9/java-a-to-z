package com.vgoryashko.trianglearea;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that performs testing of a class com.vgoryashko.trianglearea.CalcLongestSide.
 * @author vgoryashko
 * @since 15/10/2016
 * version 1.2
 */

public class CalcLongestSideTest {

	/**
	 * Method that tests calculation of a longest side.
	 */

	@Test
	public void calcLongestSideTest() {
		final int x1 = 100;
		final int y1 = 15;
		final int x2 = 15;
		final int y2 = 100;
		final int x3 = 50;
		final int y3 = 35;
		final double result = 120.20815280171308;
		Point a = new Point(x1, y1);
		Point b = new Point(x2, y2);
		Point c = new Point(x3, y3);
		CalcLongestSide calcLongest = new CalcLongestSide();
		assertThat(calcLongest.calcLongestSide(a.distanceTo(b), b.distanceTo(c), c.distanceTo(a)), is(result));
	}

	/**
	 * Method that tests calculation of a longest side.
	 */
	@Test
	public void whenInvokeWithSeveralLengthsTheMaxLengthReturned() {
		CalcLongestSide calcLongestSide = new CalcLongestSide();
		assertThat(calcLongestSide.calcLongestSide(0D, 1D, 2D), is(2D));
	}
}