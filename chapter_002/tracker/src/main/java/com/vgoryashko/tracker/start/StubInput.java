package com.vgoryashko.tracker.start;

/**
 *Class that implements stub input.
 *@author Vlad Goryashko
 *@version 0.1
 *@since 16/11/2016
 */

public class StubInput implements Input {

	/**
	 * Array of answers to be used for testing Input.
	 */
	private String[] answers;

	/**
	 * Variable to be used as index in an array of Strings.
	 */
	private int position = 0;

	/**
	 * Constructor for the class.
	 * @param aAnswers						array of answers to be sent for testing input method
     */
	public StubInput(String[] aAnswers) {
		this.answers = aAnswers;
	}

	/**
	 * Method which implements method from the interface Input.
	 * @param question						questions for the user to be displayed in the console
	 * @return								<code>String</code>
     */
	public String ask(String question) {
		return answers[position++];
	}
}