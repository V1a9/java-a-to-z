package com.vgoryashko.tracker.start;

/**
 * Class that tests class StartUI.
 * @author Vlad Goryashko
 * @version 0.3
 * @since 18/11/2016
 */

public class StartUITest {

	/**
	 * Main method - the enter point for the testing of User Interface.
	 * @param args						default parameter for main method
     */
	public static void main(String[] args) {
		Input input = new StubInput(new String[] {"create stub task"});
		new StartUI(input).init();
	}
}