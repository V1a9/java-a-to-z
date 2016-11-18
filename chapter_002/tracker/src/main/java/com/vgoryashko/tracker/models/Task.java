package com.vgoryashko.tracker.models;

/**
 * Class derived from Item and describes a Task.
 * @author vgoryashko
 * @version 0.3
 * @since 18/11/2016
 */

public class Task extends Item {

	/**
	 * Constructor for the class.
	 * @param name							A name to be set for a Task
	 * @param desc							A description to be set for a Task
	 * @param aCreateTime					A create time to be set for a Task
     */
	public Task(String name, String desc, long aCreateTime) {
		super(name, desc, aCreateTime);
	}


	/**
	 * Method that calculate price.
	 * @return								<code>"100%"</code>
     */
	public String calculatePrice() {
		return "100%";
	}
}