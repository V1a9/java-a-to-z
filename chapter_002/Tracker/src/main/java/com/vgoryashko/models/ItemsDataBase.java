package com.vgoryashko.models;

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
	
	public Item[] showAllItems(){
		Item[] result = new Item[this.position];
		for (int index = 0; index != this.position; index++){
			result[index] = this.items[index];
		}
		return result;	
	}
	
	
}
