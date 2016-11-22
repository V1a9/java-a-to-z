package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;
import com.vgoryashko.tracker.models.Bug;

/**
 * Class that implements system that implements UI for tracking system.
 * @author Vlad Goryashko
 * @version 0.3
 * @since 18/11/2016
 */

public class StartUI {

	/**
	 * Variable that is used for implementation of a number different types of input methods.
	 */
	private Input input;

	/**
	 * Constructor for the class.
	 * @param aInput							an object that implements Input interface for realization
	 *                                          of interaction with an end user.
     */
	public StartUI(Input aInput) {
		this.input = aInput;
	}

	private String[] userMainMenu = {
			"1. Create item;",
			"2. Add comment;",
			"3. Show all items;",
			"4. Remove item;",
			"5. Find item;",
			"6. Replace item;",
			"7. Exit."};

	private String[] createItemMenu = {
			"1. Create Item;",
			"2. Create Task;",
			"3. Create Bug;",
			"4. Back."};

	/**
	 * Method that initialize application.
	 */
	public void init() {
		boolean exit = false;
		int actionMainMenu = 0;
		int actionItemMenu = 0;
		Tracker tracker = new Tracker();
		Input input = new ConsoleInput();
		do {
			for (String str : this.userMainMenu) {
				System.out.println(str);
			}
			input.ask("Please enter command:");
			if (actionMainMenu == 1) {
				for (String str : this.createItemMenu) {
					System.out.println(str);
				}
				input.ask("Please choose type of request:");
					if (actionItemMenu == 1) {
						String name = null;
						String desc = null;
						name = input.ask("Please enter Item name: ");
						desc = input.ask("Please enter Item description: ");
						tracker.addItem(new Item(name, desc));
					} else {
						if (actionItemMenu == 2) {
							String name = null;
							String desc = null;
							name = input.ask("Please enter Task name: ");
							desc = input.ask("Please enter Task description: ");
							tracker.addItem(new Task(name, desc));
						} else {
							if (actionItemMenu == 3) {
								String name = null;
								String desc = null;
								name = input.ask("Please enter Bug name: ");
								desc = input.ask("Please enter Bug description: ");
								tracker.addItem(new Bug(name, desc));
							} else {
								if (actionItemMenu == 4) {
									break;
								}
							}
						}
					}
			} else {
				if (actionMainMenu == 2) {
					String name = null;
					String desc = null;
					name = input.ask("Please enter Bug name: ");
					desc = input.ask("Please enter Bug description: ");
					tracker.addItem(new Bug(name, desc));
				}
			}
		} while (exit != true);

	}

	/**
	 * Main method - the enter point for the application.
	 * @param args							array of String default parameter
     */
	public static void main(String[] args) {

//		new StartUI(input).init();
	}
}