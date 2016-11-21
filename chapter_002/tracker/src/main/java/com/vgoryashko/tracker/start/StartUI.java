package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;

/**
 * Class that implements system that implements UI for tracking system.
 * @author vgoryashko
 * @version 0.3
 * @since 18/11/2016
 */

public class StartUI {

	/**
	 * Method that initialize application.
	 */
	public void init() {
		Tracker tracker = new Tracker();
		tracker.addItem(new Task("Task_1", "Task_1 desc", 0L));
		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}
	}

	/**
	 * Main method - the enter point for the application.
	 * @param args							array of String default parameter
     */
	public static void main(String[] args) {

	}
}