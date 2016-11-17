package com.vgoryashko.trianglearea;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that performs testing of a class com.vgoryashko.trianglearea.Triangle.
 * @author vgoryashko
 * @since 14/10/2016
 * version 1.1
 */

 public class TriangleTest {

	/**
	 * Method that tests calculation of a triangles' area.
	 */

	@Test
	public void testAreaCalculation() {
		final int x1 = 100;
		final int y1 = 15;
		final int x2 = 15;
		final int y2 = 100;
		final int x3 = 50;
		final int y3 = 35;
		final double result = 1275.0000000000005;
		Triangle triangle = new Triangle(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
		assertThat(triangle.area(), is(result));
	}
 }
