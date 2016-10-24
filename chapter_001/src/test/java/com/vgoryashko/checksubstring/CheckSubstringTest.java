package com.vgoryashko.checksubstring;

/* 
 * Class that tests that a given string is a part of an origin string (com.vgoryashko.checksubstring.CheckSubstringTest)
 * @author vgoryashko
 * @since 23/10/2016
 * @version 1.0
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CheckSubstringTest{
	@Test
	public void testContains(){
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "ubstr"), is(true));
	}
}