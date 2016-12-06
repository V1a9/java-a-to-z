package com.vgoryashko.tracker.start;

/**
 * Class that implements system that implements UI for tracking system.
 * @author Vlad Goryashko
 * @version 1.0
 * @since 02/12/2016
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
	 * Variable that holds value of action 1.
	 */
	private final int action1 = 1;
	/**
	 * Variable that holds value of action 2.
	 */
	private final int action2 = 2;
	/**
	 * Variable that holds value of action 3.
	 */
	private final int action3 = 3;
	/**
	 * Variable that holds value of action 4.
	 */
	private final int action4 = 4;
	/**
	 * Variable that holds value of action 5.
	 */
	private final int action5 = 5;
	/**
	 * Variable that holds value of action 6.
	 */
	private final int action6 = 6;
	/**
	 * Variable that holds value of action 7.
	 */
	private final int action7 = 7;

	/**
	 * Range of valid input keys available for an user in the main menu.
	 */
	private int[] rangeMain = new int[]{action1, action2, action3, action4, action5, action6};

	/**
	 * Range of valid input keys available for an user in the create item menu.
	 */
	private int[] rangeCreate = new int[]{action1, action2, action3};

	/**
	 * Range of valid input keys available for an user in the find menu.
	 */
	private int[] rangeFind = new int[]{action1, action2};

	/**
	 * Range of valid input keys available for an user in the replace menu.
	 */
	private int[] rangeReplace = new int[]{action1, action2, action3};

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
			int key = Integer.valueOf(input.ask("\nSelect: ", rangeMain));
				if (key == addKey) {
					menu.showActionsCreateItem();
					menu.selectActionsCreateItem(input.ask("\nSelect: ", rangeCreate));
				} else if (key == findKey) {
					menu.showActionsFindItem();
					menu.selectActionsFindItem(input.ask("\nSelect: ", rangeFind));
				} else if (key == replaceKey) {
					menu.showActionsReplaceItem();
					menu.selectActionsReplaceItem(input.ask("\nSelect: ", rangeReplace));
				} else {
					menu.selectActionsMain(key);
				}
		} while (!"y".equals(this.input.ask("\n\nExit: y/n")));
	}

	/**
	 * Main method - the enter point for the application.
	 * @param args							array of String default parameter
     */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		Input input = new ValidateInput();
		new StartUI(input, tracker).init();
	}
}