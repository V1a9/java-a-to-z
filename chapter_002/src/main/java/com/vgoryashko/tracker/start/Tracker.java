package com.vgoryashko.tracker.start;

/*
 *Class that implements system that performs tracking of user's requests and allows to perform different actions via UI 
 *@author vgoryashko
 *@version 0.2
 *@since 14/11/2016 
 */

import com.vgoryashko.tracker.models.*;
import java.util.*;
import java.lang.System;

public class Tracker {

	private int position = 0;
	private Item[] items = new Item[10];
	protected static final Random RN = new Random();
	
	public void addItem(Item item){
		item.setId(this.generateId());
		this.items[position++] = item;
	}
	
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)){
				result = item;
				break;
			} 
		}
		return result;
	}
	
	String generateId(){
		return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
	}
	
	public void removeItem(String name, String description){
		int itemPosition = 0;
		for(int index = 0; index <= this.position - 1; index++){
			if (items[index].getName() == name && items[index].getDescription() == description){
				items[index] = null;
				itemPosition = index;
				position--;
			}	
		}
		for(int index = itemPosition; index < this.position; index++){
				items[index] = items[index + 1];
				items[index + 1] = null;
		}
	}
	
	public Item[] getAll(){
		Item[] result = new Item[this.position];
		for (int index = 0; index != this.position; index++){
			result[index] = this.items[index];
		}
		return result;	
	}
	
}
