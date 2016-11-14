package com.vgoryashko.tracker.models;
/*
 *Class that implements dataBase of Items for user's requests. Allows add\remove\search\show Items and add comments. 
 *@author vgoryashko
 *@version 0.2
 *@since 09/11/2016 
 */

public class ItemsDataBase{

	private int position = 0;
	private Item[] items;

	public ItemsDataBase(){
		this.items = new Item[100];
	}
	
	public void addItem(Item item){
		this.items[position++] = item;	
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public Item getItem(int position){
		return this.items[position];
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
	

	
	
}
