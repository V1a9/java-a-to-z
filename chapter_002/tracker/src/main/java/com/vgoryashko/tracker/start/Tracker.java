package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Comment;
import java.util.Random;

/**
 * Class that implements system that performs tracking of user's requests and allows to perform different actions via UI.
 * @author Vlad Goryashko
 * @version 0.5
 * @since 18/11/2016
 */

public class Tracker {

	/**
	 * Used as index in an array.
	 */
	private int position = 0;

	/**
	 * Number of element in the request system.
	 */
	private final int index = 10;

	/**
	 * Random values to be generated from 0 - 100.
	 */
	private final int maxId = 100;

	/**
	 * An array of customer's requests (Items).
	 */
	private Item[] items = new Item[index];

	/**
	 * Object that is used for generation of random numbers.
	 */
	protected static final Random RN = new Random();

	/**
	 * Method that adds a new Item to the tracking system.
	 * @param item							an Item to be added into array of requests.
	 * @return								<code>item</code>
	 */
	public Item addItem(Item item) {
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}

	/**
	 * Method that search an Item based on a given id.
	 * @param id							id to be used for searching of an Item
	 * @return 								<code>result</code>
	 * @throws 								InvalidRequestException if there is no a request with entered id.
	 */
	protected Item findById(String id) throws InvalidRequestException {
		Item result = null;
		boolean exists = false;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				exists = true;
				break;
			}
		}
		if (exists) {
			return result;
		} else {
			throw new InvalidRequestException("There is no a such request.");
		}
	}

	/**
	 * Method that search an Item based on a given name.
	 * @param name							name to be used for searching of an Item
	 * @return								<code>result</code>
	 * @throws 								InvalidRequestException if there is no a request with entered name.
	 */
	protected Item findByName(String name) throws InvalidRequestException {
		Item result = null;
		boolean exists = false;
		for (Item item : items) {
			if (item != null && item.getName().equals(name)) {
				result = item;
				exists = true;
				break;
			}
		}
		if (exists) {
			return result;
		} else {
			throw new InvalidRequestException("There is no request with such name.");
		}
	}

	/**
	 * Method that generates id.
	 * @return								<code>id</code>
	 */
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt(maxId));
	}

	/**
	 * Method that removes an Item based on an given id.
	 * @param itemId						an id of an Item to be removed
	 * @throws 								InvalidRequestException if there is no a request with entered id.
	 */
	public void removeItem(String itemId) throws InvalidRequestException {
		int itemPosition = 0;
		boolean isRemoved = false;
		for (int aIndex = 0; aIndex <= this.position - 1; aIndex++) {
			if (items[aIndex].getId().equals(itemId)) {
				items[aIndex] = null;
				itemPosition = aIndex;
				isRemoved = true;
				position--;
			}
		}
		if (isRemoved) {
			for (int aIndex = itemPosition; aIndex < this.position; aIndex++) {
				items[aIndex] = items[aIndex + 1];
				items[aIndex + 1] = null;
				}
		} else {
			throw new InvalidRequestException("\nThere is no a request with such id.");
		}
	}

	/**
	 * Method that gets all Items.
	 * @return result							<code>result</code>
	 */
	public Item[] getAll() {
		Item[] result = new Item[this.position];
		for (int aIndex = 0; aIndex != this.position; aIndex++) {
			result[aIndex] = this.items[aIndex];
		}
		return result;
	}

	/**
	 * Method that adds a Comment to an Item.
	 * @param name								name of an Item which a comment to be added to
	 * @param comm								comment to be added to an Item
	 * @return									<code>item</code>
	 */
	public Item addComment(String name, String comm) {
		Item item = null;
		for (int aIndex = 0; aIndex != this.position; aIndex++) {
			if (this.items[aIndex].getName().equals(name)) {
				Comment comment = new Comment(comm);
				this.items[aIndex].setComment(comment);
				item = this.items[aIndex];
			}
		}
		return item;
	}

	/**
	 * Method that edits Item content.
	 * @param item								an Item to be edited
	 * @return									<code>boolean</code>
	 */
	public boolean replace(Item item) {
		boolean result = false;
		for (int aIndex = 0; aIndex != this.position; aIndex++) {
			if (this.items[aIndex] != null && this.items[aIndex].getId().equals(item.getId())) {
				this.items[aIndex] = item;
				result = true;
				break;
			}
		}
		return result;
	}
}
