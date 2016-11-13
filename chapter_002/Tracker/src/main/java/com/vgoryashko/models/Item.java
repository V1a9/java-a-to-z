package com.vgoryashko.models;

/*
 *Class that implements Item for an user's requests
 *@author vgoryashko
 *@version 0.1
 *@since 08/11/2016 
 */
 
import java.util.*;

public class Item {

    private String name;
	private String description;
	private String id;
	private String createTime;
	private static final Random RN = new Random();
	//private Comment[] comments;
	
	public Item() {
	
	}

	public Item (String name, String description) {
		this.name = name;
		this.description = description;
		this.createTime = new Date(System.currentTimeMillis()).toString();
		this.id = this.generateId();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	
	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}
	
	public String generateId(){
		return String.valueOf(RN.nextInt(100));
	}

}
