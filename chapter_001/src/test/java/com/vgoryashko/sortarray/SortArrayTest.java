package com.vgoryashko;

/* 
 * Class that tests sorting of a given array of integers (com.vgoryashko.sortarray.SortArray)
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.0
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortArrayTest{
	
	@Test
	public void whenGivenArrayThenItIsSorted() {
		SortArray sortArray = new SortArray();
		int[] values = new int[]{10, 2, 3, 8, 5, 4, 6, 9, 1, 7};
		//assertArrayEquals(sortArray.sort(values), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		assertThat(sortArray.sort(values), is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}
}