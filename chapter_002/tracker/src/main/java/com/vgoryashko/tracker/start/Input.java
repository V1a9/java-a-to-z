package com.vgoryashko.tracker.start;

/**
 * Interface that describes input method.
 * @author vgoryashko
 * @version 0.2
 * @since 18/11/2016
 */

public interface Input {

	/**
	 *
	 * @param question						question for the user to be displayed in the console
	 * @return								<code>String</code>
     */
	String ask(String question);
}