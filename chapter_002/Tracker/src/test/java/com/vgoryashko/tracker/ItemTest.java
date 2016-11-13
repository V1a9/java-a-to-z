package com.vgoryashko.models;

/*
 *Class that tests some getters/setters of Item class.
 *@author vgoryashko
 *@version 0.1
 *@since 09/11/2016 
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


public class ItemTest{

	@Test
	public void setNameTest(){
		Item item = new Item();
		item.setName("Item1");
		assertThat(item.getName(), is("Item1"));
	}
	
	@Test
	public void getNameTest(){
		Item item = new Item("Item1", "desc");
		assertThat(item.getName(), is("Item1"));
	}
	
	@Test
	public void setDescriptionTest(){
		Item item = new Item();
		item.setDescription("desc");
		assertThat(item.getDescription(), is("desc"));
	}
	
	@Test
	public void getDescriptionTest(){
		Item item = new Item();
		item.setDescription("desc");
		assertThat(item.getDescription(), is("desc"));
	}
}