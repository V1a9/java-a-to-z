package com.vgoryashko.sortarray;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

/**
 * Class that tests sorting of a given array of integers (com.vgoryashko.sortarray.SortArray).
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.0
 */

public class SortArrayTest {

	/**
	 * Method that tests sorting of a given array of integers (com.vgoryashko.sortarray.SortArray).
	 */

	@Test
	public void whenGivenArrayThenItIsSorted() {
		SortArray sortArray = new SortArray();
		final int[] values = new int[]{10, 2, 3, 8, 5, 4, 6, 9, 1, 7};
		final int[] result = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		assertArrayEquals(sortArray.sort(values), result);
	}
}