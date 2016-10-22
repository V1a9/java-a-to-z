package com.vgoryashko.removedublicates;

/* 
 * Class that tests removing of dublicates in a given array of Strings(com.vgoryashko.removedublicates.RemoveDublicates)
 * @author vgoryashko
 * @since 21/10/2016
 * @version 1.0
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RemoveDublicatesTest {

	@Test
	public void removeDublicates(){
		RemoveDublicates removeDublicates = new RemoveDublicates();
		String[] stringArray = new String[6];
		stringArray[0] = "asdfa";
		stringArray[1] = "adfa";
		stringArray[2] = "aaaa";
		stringArray[3] = "asdfa";
		stringArray[4] = "bbbbb";
		stringArray[5] = "aaaa";
		assertThat(removeDublicates.remove(stringArray), is(new String[] {"asdfa", "adfa", "aaaa", null, "bbbbb", null}));
	}
}