package com.vgoryashko.sortarray;

/* 
 * Class that sorts a given array of integers
 * @author vgoryashko
 * @since 20/10/2016
 * @version 1.0
 */
 
 import java.util.*;
 
 public class SortArray{
	public int[] sort(int[] values){
		int changeValue;
		for (int i = 0; i < values.length - 1; i++){
			for (int j = 0; j < values.length - 1; j++){
				if (values[j] > values[j + 1]) {
					changeValue = values[j + 1];
					values[j + 1] = values[j];
					values[j] = changeValue;
				}
			}
		}
		return values;
	}
}