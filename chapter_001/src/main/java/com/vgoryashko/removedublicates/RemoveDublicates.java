package com.vgoryashko.removedublicates;

/* 
 * Class that removes dublicates in a given array of Strings
 * @author vgoryashko
 * @since 23/10/2016
 * @version 1.1
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
		
		String buffer;
		int offset = 0;
		for(int i = 0; i < result.length; i++){
			for (int j = i + 1; j < (result.length - 1) - offset; j++){
				if (result[i] == null){
					buffer = result[i];
					result[i] = result[j];
					result[j] = buffer;
					offset++;
				}
			}
		}
		
		return result;
	}
 }