package com.vgoryashko.tracker.start;

/**
 * Class that implements system that implements UI for tracking system.
 * @author vgoryashko
 * @version 0.3
 * @since 18/11/2016
 */

import com.vgoryashko.tracker.models.*;

public class StartUI {

	private Input input;
	
	public StartUI(Input input) {
		this.input = input;
	}

	public void init() {
		String name = input.ask("Please enter a task's name: ");
		Tracker tracker = new Tracker();
		tracker.addItem(new Task(name, "Task_1 desc"));
		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}			
	}

	public static void main (String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}