package com.vgoryashko.removedublicates;

/* 
 * Class that tests removing of dublicates in a given array of Strings(com.vgoryashko.removedublicates.RemoveDublicates)
 * @author vgoryashko
 * @since 23/10/2016
 * @version 1.1
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RemoveDublicatesTest {

	@Test
	public void removeDublicates(){
		RemoveDublicates removeDublicates = new RemoveDublicates();
		String[] stringArray = new String[]{"bbbbb", "asdfa", "adfa", "aaaa", "asdfa", "bbbbb", "aaaa", "adfa", "bbbbb"};
		assertThat(removeDublicates.remove(stringArray), is(new String[] {"bbbbb", "asdfa", "adfa", "aaaa", null, null, null, null, null}));
	}
}