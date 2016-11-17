package com.vgoryashko.tracker.models;

/*
 *Class derived from Item 
 *@author vgoryashko
 *@version 0.1
 *@since 15/11/2016 
 */
 
 public class Comment extends Item {
	public String comment;
	public Comment (String comment) {
		this.comment = comment;
	}
 }