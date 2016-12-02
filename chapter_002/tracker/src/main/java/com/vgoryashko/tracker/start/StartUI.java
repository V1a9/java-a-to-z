package com.vgoryashko.tracker.start;

/**
 * Class that implements system that implements UI for tracking system.
 * @author Vlad Goryashko
 * @version 0.5
 * @since 01/12/2016
 */

public class StartUI {

	/**
	 * Variable that is used for implementation of a number different types of input methods.
	 */
	private Input input;

	/**
	 * Variable that is used for operating with the class Tracker.
	 */
	private Tracker tracker;

	/**
	 * Constructor for the class.
	 * @param aInput							an object that implements Input interface for realization
	 *                                          of interaction with an end user.
	 * @param aTracker							an object that implements Tracking system.
     */
	public StartUI(Input aInput, Tracker aTracker) {
		this.input = aInput;
		this.tracker = aTracker;
	}

	/**
	 * Variable that represents key number for adding a new request in the main menu.
	 */
	private final int addKey = 1;

	/**
	 * Variable that represents key number for searching a request in the main menu.
	 */
	private final int findKey = 5;

	/**
	 * Variable that represents key number for replacing a request in the main menu.
	 */
	private final int replaceKey = 6;

	/**
	 * Method that initialize application.
	 */
	public void init() {
		TrackerMenu menu = new TrackerMenu(tracker, this.input);
		menu.fillActionsMain();
		menu.fillActionsCreateItem();
		menu.fillActionsFindItem();
		menu.fillActionsReplaceItem();
		do {
			menu.showActionsMain();
			int key = Integer.valueOf(input.ask("\nSelect: "));
				if (key == addKey) {
					menu.showActionsCreateItem();
					key = Integer.valueOf(input.ask("\nSelect: "));
					menu.selectActionsCreateItem(key);
				} else if (key == findKey) {
					menu.showActionsFindItem();
					key = Integer.valueOf(input.ask("\nSelect: "));
					menu.selectActionsFindItem(key);
				} else if (key == replaceKey) {
					menu.showActionsReplaceItem();
					key = Integer.valueOf(input.ask("\nSelect: "));
					menu.selectActionsReplaceItem(key);
				} else {
					menu.selectActionsMain(key);
				}
		} while (!"y".equals(this.input.ask("\nExit: y/n")));
	}

	/**
	 * Main method - the enter point for the application.
	 * @param args							array of String default parameter
     */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		Input input = new ConsoleInput();
		new StartUI(input, tracker).init();
	}
}