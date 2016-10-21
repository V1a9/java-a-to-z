package com.vgoryashko;

/* 
 * Class that removes dublicates in a given array of Strings
 * @author vgoryashko
 * @since 21/10/2016
 * @version 1.0
 */
 
 public class RemoveDublicates{
	public String[] remove(String[] string){
		String[] result = new String[string.length];
		result[0] = string[0];
		for (int i = 0; i < string.length; i++){
			for (int j = i + 1; j < string.length; j++){
				if (string[j] == null) {
					continue;
				} else if (string[i].equals(string[j])) {
					result[j] = null;
				}
				else result[j] = string[j];
			}
		}
		return result;
	}
 }