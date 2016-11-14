package com.vgoryashko.tracker.models;

/*
 *Class derived from Item
 *@author vgoryashko
 *@version 0.2
 *@since 14/11/2016 
 */

public class Task extends Item{
	public Task(String name, String desc){
		this.name = name;
		this.description = desc;
	}
	
	public String calculatePrice(){
		return "100%";
	}
}