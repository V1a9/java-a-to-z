package com.vgoryashko.rotatearray;

/**
 * Class that rotates on 90 degrees a given multi-dimensional array of integers.
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.0
 */

 public class RotateArray {

	/**
	 * Method that rotates on 90 degrees a given multi-dimensional array of integers.
	 * @param values - multi-dimensional array of integers
	 * @return result - multi-dimensional array of integers
	 */

 public int[][] rotate(int[][] values) {
		int[][] result = new int[values[0].length][values.length];
		for (int row = 0; row < values.length; row++) {
			for (int col = 0; col < values[row].length; col++) {
				result[col][row] = values[row][col];
			}
		}
		return result;
	}
}