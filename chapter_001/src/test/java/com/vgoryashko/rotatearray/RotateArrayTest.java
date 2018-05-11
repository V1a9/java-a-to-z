package com.vgoryashko.rotatearray;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests rotation of a given two-dimensional array of integers on 90 degrees (com.vgoryashko.rotatearray.RotateArray).
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.0
 */

public class RotateArrayTest {

	/**
	 * Method that tests rotation of a given two-dimensional array of integers on 90 degrees (com.vgoryashko.rotatearray.RotateArray).
	 */

	@Test
	public void whenGivenArrayThenItIsRotated() {
		RotateArray rotateArray = new RotateArray();
		final int[][] values = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
		final int[][] result = new int[][]{{1, 6}, {2, 7}, {3, 8}, {4, 9}, {5, 10}};
		assertThat(rotateArray.rotate(values), is(result));
	}
}