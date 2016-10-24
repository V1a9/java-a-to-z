package com.vgoryashko.checksubstring;

/* 
 * Class that checks that a given string is a part of an origin string (com.vgoryashko.checksubstring.CheckSubstring)
 * @author vgoryashko
 * @since 23/10/2016
 * @version 1.0
 */

import java.lang.String;

public class CheckSubstring{
	public boolean contains(String origin, String sub){
		boolean result = true;
		char[] originArray = origin.toCharArray();
		char[] subArray = sub.toCharArray();
		for(int i = 0; i < originArray.length; i++){
			for(int j = 0; j < subArray.length; j++){
				if (subArray[j] == originArray[i]){
					//result = true;
				} else {
					result = false;
				}
			}
			
		}
		return result;
	}
	
}