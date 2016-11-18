package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.*;
import java.util.*;
import java.lang.System;

/**
 * Class that implements system that performs tracking of user's requests and allows to perform different actions via UI.
 * @author vgoryashko
 * @version 0.4
 * @since 17/11/2016
 */

public class Tracker {

	private int position = 0;
	public Item[] items = new Item[10];
	protected static final Random RN = new Random();
	
	/**
	 * Method that adds a new Item to the tracking system.
	 * @param item
	 * @return item
	 */
	
	public Item addItem(Item item) {
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}
	
	/**
	 * Method that search an Item based on a given id.
	 * @param id
	 * @return result
	 */
	
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			} 
		}
		return result;
	}
	
	/**
	 * Method that search an Item based on a given name.
	 * @param name
	 * @return result
	 */
	
	protected Item findByName(String name) {
		Item result = null;
		for (Item item : items) {
			if (item.getName().equals(name)) {
				result = item;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Method that generates id.
	 * @return String
	 */
	
	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
	}
	
	/**
	 * Method that removes an Item.
	 * @param name, description
	 * @return result
	 */	
	
	public void removeItem(String name, String description) {
		int itemPosition = 0;
		boolean isRemoved = false;
		Item[] result = null;
		for (int index = 0; index <= this.position - 1; index++) {
			if (items[index].getName().equals(name) && items[index].getDescription().equals(description)) {
				items[index] = null;
				itemPosition = index;
				isRemoved = true;
				position--;
			}	
		}
		if (isRemoved == true) {
			for(int index = itemPosition; index < this.position; index++) {
					items[index] = items[index + 1];
					items[index + 1] = null;
			}
		}
	}
	
	/**
	 * Method that gets all Items.
	 * @return result
	 */
	
	public Item[] getAll() {
		Item[] result = new Item[this.position];
		for (int index = 0; index != this.position; index++) {
			result[index] = this.items[index];
		}
		return result;	
	}
	
	/**
	 * Method that adds a Comment to an Item.
	 * @param name
	 * @param comm
	 * @return item
	 */

	public Item addComment(String name, String comm) {
		Item item = null;
		for (int index =0; index != this.position; index++) {
			if (this.items[index].getName().equals(name)) {
				Comment comment = new Comment(comm);
				this.items[index].setComment(comment);
				item = this.items[index];
			}
		}
		return item;
	}
}