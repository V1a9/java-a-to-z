package com.vgoryashko.tracker.start;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import com.vgoryashko.tracker.models.Item;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

/**
 * Class that tests methods of Tracker class.
 * @author vgoryashko
 * @version 3.1
 * @since 24/04/2017
 */

public class TrackerTest {

	/**
	 * Variable that is used for operating with the class Tracker.
	 */
	private Tracker tracker;
	/**
	 * Variable that is used for manipulations with request item1.
	 */
	private Item item1;
	/**
	 * Variable that is used for manipulations with request item2.
	 */
	private Item item2;
	/**
	 * Variable that is used for manipulations with request item3.
	 */
	private Item item3;
	/**
	 * Variable that is used for manipulations with request item4.
	 */
	private Item item4;

	/**
	 * For testing of exceptions.
	 */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Method that instantiates variables and setting up environments.
	 */
	@Before
	public void setUp() {
		tracker = new Tracker();
		item1 = new Item("Item_1", "Desc_1");
		item2 = new Item("Item_2", "Desc_2");
		item3 = new Item("Item_3", "Desc_3");
		item4 = new Item("Item_04", "Desc_04");
		tracker.addItem(item1);
		tracker.addItem(item2);
		tracker.addItem(item3);
	}

	/**
	 * Method that tests an Item addition to the system.
	 */
	@Test
	public void addItemTest() {
		assertThat(tracker.addItem(item1), is(item1));
	}

	/**
	 * Method that test search an Item by id.
	 */
	@Test
	public void findByNameTest() {
		assertThat(tracker.findByName("Item_2"), is(item2));
	}

	/**
	 * Method that tests findById() method from the class Tracker.
	 */

	@Test
	public void findByIdTest() {
		assertThat(tracker.findById(item2.getId()), is(item2));
	}

	/**
	 * Method that test removing of an Item from the system.
	 */
	@Test
	public void removeItemTest() {
		tracker.removeItem(item1.getId());
		assertTrue(true);
	}

	/**
	 * Method that tests depicting of all Items.
	 */
	@Test
	public void getAllTest() {
		List<Item> list = Arrays.asList(item1, item2, item3);
		assertTrue(tracker.getAll().equals(list));
	}

	/**
	 * Method that tests addition of a Comment to an Item.
	 */
	@Test
	public void addCommentTest() {
		assertThat(tracker.addComment("Item_1", "new comment is added"), is(item1));
	}

	/**
	 * Method for testing method editItem() from the class Tracker.
	 */
	@Test
	public void replaceTest() {
		item4.setId(item2.getId());
		assertThat(tracker.replace(item4), is(true));
		List<Item> list = Arrays.asList(item1, item4, item3);
		assertTrue(tracker.getAll().equals(list));
	}
}