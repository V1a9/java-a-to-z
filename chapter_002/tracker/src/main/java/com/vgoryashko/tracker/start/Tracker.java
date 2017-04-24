package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that implements system that performs tracking of user's requests and allows to perform different actions via UI.
 * @author Vlad Goryashko
 * @version 3.1
 * @since 24/04/2017
 */

public class Tracker {

	/**
	 * Random values to be generated from 0 - 100.
	 */
	private final int maxId = 100;

	/**
	 * An array of customer's requests (Items).
	 */
	private List<Item> items = new ArrayList<>();

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
		this.items.add(item);
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

		boolean found = false;
		int index = 0;

		for (Item item : this.items) {
			if (item.getId().equals(itemId)) {
				index = this.items.indexOf(item);
				found = true;
			}
		}

		if (found) {
			this.items.remove(index);
		} else {
			throw new InvalidRequestException("\nThere is no a request with such id.");
		}
	}

	/**
	 * Method that gets all Items.
	 * @return result							<code>result</code>
	 */
	public List<Item> getAll() {

		return this.items;

	}

	/**
	 * Method that adds a Comment to an Item.
	 * @param name								name of an Item which a comment to be added to
	 * @param comm								comment to be added to an Item
	 * @return									<code>item</code>
	 */
	public Item addComment(String name, String comm) {

		Item item = null;

		for (Item aItem : this.items) {
			if (aItem.getName().equals(name)) {
				Comment comment = new Comment(comm);
				aItem.setComment(comment);
				item = aItem;
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

		for (Item aItem : this.items) {
			if (aItem.getId().equals(item.getId())) {
				this.items.set(this.items.indexOf(aItem), item);
				result = true;
				break;
			}
		}

		return result;
	}
}
