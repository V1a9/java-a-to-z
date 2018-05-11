package com.vgoryashko.connectarrays;

 /**
 * Class that performs connection of two arrays sorted in ascending order.
 * @author vgoryashko
 * @since 1.11.2016
 * @version 1.1
 */

public class ConnectArrays {

	/**
	 * Method that connects two arrays.
	 * @param array1 - first array
	 * @param array2 - second array
	 * @return result - new array built from the two given arrays
	 */

    public int[] connectArrays(int[] array1, int[] array2) {
	int resultLength = array1.length + array2.length;
	int[] result = new int[resultLength];
	if (array1[0] > array2[array2.length - 1]) {
	    for (int i = 0; i < array2.length; i++) {
			result[i] = array2[i];
	    }
		int j = array2.length;
	    for (int i = 0; i < array1.length; i++) {
			result[j++] = array1[i];
		}
	} else {
	    for (int i = 0; i < array1.length; i++) {
			result[i] = array1[i];
		}
		int j = array1.length;
	    for (int i = 0; i < array2.length; i++) {
			result[j++] = array2[i];
		}
	}
	return result;
    }
}
