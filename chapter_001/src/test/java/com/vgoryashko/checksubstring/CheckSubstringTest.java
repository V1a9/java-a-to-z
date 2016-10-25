package com.vgoryashko.checksubstring;

/* 
 * Class that tests that a given string is a part of an origin string (com.vgoryashko.checksubstring.CheckSubstringTest)
 * @author vgoryashko
 * @since 25/10/2016
 * @version 1.1
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CheckSubstringTest{
	@Test
	public void testContainsManyLetters(){
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "ubstr"), is(true));
	}

	@Test
	public void testContainsOneLetter(){
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "s"), is(true));
	}

	@Test
	public void testContainsZeroLetters(){
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", ""), is(false));
	}

	@Test
	public void testContainsWrongSymbols(){
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "uBstr"), is(false));
	}
}