package com.vgoryashko.tracker.start;

/*
 *Class that tests methods of Tracker class.
 *@author vgoryashko
 *@version 0.1
 *@since 09/11/2016 
 */

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import com.vgoryashko.tracker.models.*;

public class TrackerTest {

	/*@Test
	public void addItemTest() {
		Tracker tracker = new Tracker();
		assertSame(tracker.addItem(new Item("Item_0", "Desc_0", 0l)), tracker.items[0]);
	}*/
	
	/*@Test
	public void removeItemTest(){
		ItemsDataBase itemsDataBase = new ItemsDataBase();
		Item item0 = new Item("Item_0", "Desc_0");
		Item item1 = new Item("Item_1", "Desc_1");
		Item item2 = new Item("Item_2", "Desc_2");
		Item item3 = new Item("Item_3", "Desc_3");
		Item item4 = new Item("Item_4", "Desc_4");
		itemsDataBase.addItem(item0);
		itemsDataBase.addItem(item1);
		itemsDataBase.addItem(item2);
		itemsDataBase.addItem(item3);
		itemsDataBase.addItem(item4);
		itemsDataBase.removeItem("Item_2", "Desc_2");
		assertEquals(item0, itemsDataBase.getItem(0));
		assertEquals(item1, itemsDataBase.getItem(1));
		assertEquals(item3, itemsDataBase.getItem(2));
		assertEquals(item4, itemsDataBase.getItem(3));
		assertNull(itemsDataBase.getItem(4));
	}*/
	
	@Test
	public void createTimeTest(){
		
	}
}