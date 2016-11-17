package com.vgoryashko.checksubstring;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests that a given string is a part of an origin string (com.vgoryashko.checksubstring.CheckSubstringTest).
 * @author vgoryashko
 * @since 25/10/2016
 * @version 1.1
 */

public class CheckSubstringTest {

	/**
	 * Method that tests a case when substring has many letters.
	 */

	@Test
	public void testContainsManyLetters() {
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "ubstr"), is(true));
	}

	/**
	 * Method that tests a case when substring has only one letter.
	 */

	@Test
	public void testContainsOneLetter() {
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "s"), is(true));
	}

	/**
	 * Method that tests a case when substring has no letters.
	 */

	@Test
	public void testContainsZeroLetters() {
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", ""), is(false));
	}

	/**
	 * Method that tests a case when substring has wrong letters.
	 */

	@Test
	public void testContainsWrongSymbols() {
		CheckSubstring checkSubstring = new CheckSubstring();
		assertThat(checkSubstring.contains("Substring", "uBstr"), is(false));
	}
}