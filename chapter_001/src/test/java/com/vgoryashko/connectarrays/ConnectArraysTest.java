package com.vgoryashko.connectarrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class that tests class which performs connection of two arrays sorted in ascending order.
 *@author vgoryashko
 *@since 31.10.2016
 *@version 1.0
 */

public class ConnectArraysTest {

	/**
	 * Method that tests connection of two arrays.
	 */

    @Test
    public void connectArraysTest() {
		ConnectArrays connect = new ConnectArrays();
		final int[] array1 = new int[]{0, 1, 2, 3};
		final int[] array2 = new int[]{5, 7, 8, 11};
		final int[] result1 = new int[]{0, 1, 2, 3, 5, 7, 8, 11};
		final int[] array3 = new int[]{5, 6, 7, 9};
		final int[] array4 = new int[]{1, 2, 4};
		final int[] result2 = new int[]{1, 2, 4, 5, 6, 7, 9};
        assertThat(connect.connectArrays(array1, array2), is(result1));
		assertThat(connect.connectArrays(array3, array4), is(result2));
    }
}
