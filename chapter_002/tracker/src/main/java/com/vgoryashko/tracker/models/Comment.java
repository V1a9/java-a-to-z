package com.vgoryashko.tracker.models;

/**
 * Class derived from Item and used to add comment to a request.
 * @author vgoryashko
 * @version 0.2
 * @since 18/11/2016
 */

 public class Comment extends Item {

	/**
	 * Variable comment.
	 */
	private String comment;

	/**
	 * Constructor for the class.
	 * @param comm - stores string description for a comment
     */
	public Comment(String comm) {
		this.comment = comm;
	}
 }