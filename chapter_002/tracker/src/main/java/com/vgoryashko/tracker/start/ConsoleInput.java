package com.vgoryashko.tracker.start;

	/*
	 *Clas that implements interface Input
	 *@author vgoryashko
	 *@version 0.1
	 *@since 16/11/2016 
	 */

import java.util.*;

public class ConsoleInput implements Input {
	
	private Scanner scanner = new Scanner(System.in);
	
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
	 
}