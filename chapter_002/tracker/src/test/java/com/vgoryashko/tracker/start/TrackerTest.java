package com.vgoryashko.tracker.start;

/**
 * Class that tests methods of Tracker class.
 * @author vgoryashko
 * @version 0.3
 * @since 18/11/2016
 */

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import com.vgoryashko.tracker.models.*;

public class TrackerTest {

	/**
	 * Method that tests an Item addition to the system.
	 */

	@Test
	public void addItemTest() {
		Tracker tracker = new Tracker();
		Item item = new Item("Item_1", "Desc_1", 0l);
		assertThat(tracker.addItem(item), is(item));
	}

	/**
	 * Method that test search an Item by id.
	 */
	
	@Test
	public void findByNameTest() {
		Tracker tracker = new Tracker();
		Item item1 = new Item("Item_1", "Desc_1", 0l);
		Item item2 = new Item("Item_2", "Desc_2", 1l);
		Item item3 = new Item("Item_3", "Desc_3", 2l);
		tracker.addItem(item1);
		tracker.addItem(item2);
		tracker.addItem(item3);
		assertThat(tracker.findByName("Item_2"), is(item2));
	}

	/**
	 * Method that test removing of an Item from the system.
	 */

	@Test
	public void removeItemTest() {
		Tracker tracker = new Tracker();
		Item item1 = new Item("Item_1", "Desc_1", 0l);
		Item item2 = new Item("Item_2", "Desc_2", 1l);
		Item item3 = new Item("Item_3", "Desc_3", 2l);
		Item item4 = new Item("Item_4", "Desc_4", 0l);
		Item item5 = new Item("Item_5", "Desc_5", 1l);
		Item item6 = new Item("Item_6", "Desc_6", 2l);
		tracker.addItem(item1);
		tracker.addItem(item2);
		tracker.addItem(item3);
		tracker.addItem(item4);
		tracker.addItem(item5);
		tracker.addItem(item6);
		tracker.removeItem("Item_3", "Desc_3");
		assertThat(tracker.items, is(new Item[]{item1, item2, item4, item5, item6, null, null, null, null, null}));
	}

	/**
	 * Method that tests depicting of all Items.
	 */

	@Test
	public void getAllTest() {
		Tracker tracker = new Tracker();
		Item item1 = new Item("Item_1", "Desc_1", 0l);
		Item item2 = new Item("Item_2", "Desc_2", 1l);
		Item item3 = new Item("Item_3", "Desc_3", 2l);
		Item item4 = new Item("Item_4", "Desc_4", 0l);
		Item item5 = new Item("Item_5", "Desc_5", 1l);
		Item item6 = new Item("Item_6", "Desc_6", 2l);
		tracker.addItem(item1);
		tracker.addItem(item2);
		tracker.addItem(item3);
		tracker.addItem(item4);
		tracker.addItem(item5);
		tracker.addItem(item6);
		assertThat(tracker.getAll(), is(new Item[]{item1, item2, item3, item4, item5, item6}));
	}

	/**
	 * Method that tests addition of a Comment to an Item.
	 */

	@Test
	public void addCommentTest(){
		Tracker tracker = new Tracker();
		Item item1 = new Item("Item_1", "Desc_1", 0l);
		tracker.addItem(item1);
		assertThat(tracker.addComment("Item_1", "new comment is added"), is(item1));
	}
}