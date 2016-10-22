package com.vgoryashko.removedublicates;

/* 
 * Class that removes dublicates in a given array of Strings
 * @author vgoryashko
 * @since 22/10/2016
 * @version 1.0
 */
 
 public class RemoveDublicates{
	public String[] remove(String[] string){
		String[] result = new String[string.length];
		for (int i = 0; i < string.length; i++){
			for (int j = i + 1; j < string.length; j++){
				if (string[i].equals(string[j])) {
					result[j] = "dublicate";
				}
			}
		}
		
		for (int i = 0; i < string.length; i++){
			if (result[i] == null){
				result[i] = string[i];
			}
			else
				result[i] = null;
		}
		return result;
	}
 }