package com.vgoryashko.tracker.models;

/**
 * Class that implements Item for an user's requests.
 * @author vgoryashko
 * @version 0.2
 * @since 15/11/2016
 */

public class Item {

	/**
	 * Default constructor.
	 */
    public Item() {
	}

	/**
	 * Instance variable name.
	 */
	private String name;

	/**
	 * Instance variable description.
	 */
	private String description;

	/**
	 * Instance variable id.
	 */
	private String id;

	/**
	 * Instance variable createTime.
	 */
	private long createTime;

	/**
	 * Instance variable comment.
	 */
	private Comment comment;

	/**
	 * Constructor for the class.
	 * @param aName				a name to be set for an Item
	 * @param aDescription		a description to be set for an Item
     */
	public Item(String aName, String aDescription) {
		this.name = aName;
		this.description = aDescription;
		this.createTime = this.getCreateTime();
	}

	/**
	 * Getter for the field name.
	 * @return 					<code>name</code>
     */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for the field description.
	 * @return 					<code>description</code>
     */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Getter for the field createTime.
	 * @return 					<code>createTime</code>
     */
	public long getCreateTime() {
		return this.createTime;
	}

	/**
	 * Getter for the field id.
	 * @return 					<code>id</code>
     */
	public String getId() {
		return this.id;
	}

	/**
	 * Setter for the field id.
	 * @param aId				Value to be set for id
     */
	public void setId(String aId) {
		this.id = aId;
	}

	/**
	 * Setter for the field comment.
	 * @param aComment			Value to be set for comment
     */
	public void setComment(Comment aComment) {
		this.comment = aComment;
	}
}
