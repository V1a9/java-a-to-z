package com.vgoryashko.tracker.start;

import java.util.Scanner;

/**
 * Class that implements interface Input.
 * @author vgoryashko
 * @version 0.2
 * @since 18/11/2016
 */

public class ConsoleInput implements Input {

	/**
	 * scanner object is used for console input.
	 */
	private Scanner scanner = new Scanner(System.in);


	/**
	 * Method that implements console input.
	 * @param question					question that will be printed in console
	 * @return							<code>string read from the console</code>
     */
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
}