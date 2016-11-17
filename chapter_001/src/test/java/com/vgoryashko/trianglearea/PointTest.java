package com.vgoryashko.trianglearea;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that performs testing of a class com.vgoryashko.trianglearea.Point.
 * @author vgoryashko
 * @since 14/10/2016
 * version 1.1
 */

public class PointTest {

	/**
	 * Method that tests calculation of distancies between two points.
	 */

	@Test
	public void testDistanceTo() {
		final int x1 = 100;
		final int y1 = 15;
		final int x2 = 15;
		final int y2 = 100;
		final int x3 = 50;
		final int y3 = 35;
		final double result1 = 120.20815280171308;
		final double result2 = 73.824115301167002;
		final double result3 = 53.851648071345040;
		Point a = new Point(x1, y1);
		Point b = new Point(x2, y2);
		Point c = new Point(x3, y3);
		assertThat(a.distanceTo(b), is(result1));
		assertThat(b.distanceTo(c), is(result2));
		assertThat(c.distanceTo(a), is(result3));
	}
}