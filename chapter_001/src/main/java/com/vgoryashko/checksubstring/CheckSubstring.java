package com.vgoryashko.checksubstring;

/**
 * Class that checks that a given string is a part of an origin string (com.vgoryashko.checksubstring.CheckSubstring).
 * @author vgoryashko
 * @since 25/10/2016
 * @version 1.3
 */

public class CheckSubstring {

/**
 * Method that checks if a given substring is a part of origin string.
 * @param origin - an origing string
 * @param sub - a given sub-string
 * @return result - true or false
 */
	public boolean contains(String origin, String sub) {
		boolean result = true;
		char[] originArray = origin.toCharArray();
		char[] subArray = sub.toCharArray();
		if (subArray.length == 0 | subArray.length > originArray.length) {
			result = false;
		} else {
			for (int i = 0; i < originArray.length - subArray.length; i++) {
				if (originArray[i] == subArray[0] & originArray.length >= subArray.length) {
					for (char c : subArray) {
						if (c == originArray[i]) {
							i++;
						} else {
							result = false;
							break;
						}
					}
					break;
				}
			}
		}
	return result;
	}
}