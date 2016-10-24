package com.vgoryashko.checksubstring;

/* 
 * Class that checks that a given string is a part of an origin string (com.vgoryashko.checksubstring.CheckSubstring)
 * @author vgoryashko
 * @since 24/10/2016
 * @version 1.1
 */

import java.lang.String;

public class CheckSubstring{
	public boolean contains(String origin, String sub){
		boolean result = false;
		char[] originArray = origin.toCharArray();
		char[] subArray = sub.toCharArray();		
		if (originArray.length < subArray.length) {
			result = false;
		} else {
			for (int i = 0; i < originArray.length && i < subArray.length;){
				if (subArray[0] == originArray[i]){
					for(int j = 1; j < subArray.length; j++) {
						if (subArray[j] == originArray[i++]){
							if (j == subArray.length - 1) {
								result = true;
							} else {
								continue;
							}
						} else {
							result = false;
						}
					}
				}
			}
		}
		return result;
	}
}