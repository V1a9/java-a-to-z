package com.vgoryashko.tracker.models;

/*
 *Class that implements Item for an user's requests
 *@author vgoryashko
 *@version 0.2
 *@since 15/11/2016 
 */
 
import java.util.*;

public class Item {

    public Item() {
	
	}
	
	public String name;
	public String description;
	private String id;
	public long createTime;
	private Comment comment;
		
	public Item (String name, String description, long createTime) {
		this.name = name;
		this.description = description;
		this.createTime = createTime;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public long getCreateTime(){
		return this.createTime;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setComment(Comment comment){
		this.comment = comment;
	}
}
