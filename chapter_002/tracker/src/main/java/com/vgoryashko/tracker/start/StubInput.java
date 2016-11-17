package com.vgoryashko.tracker.start;

	/*
	 *Clas that implements
	 *@author vgoryashko
	 *@version 0.1
	 *@since 16/11/2016 
	 */

public class StubInput implements Input {
	
	private String[] answers;
	private int position = 0;
	
	public StubInput(String[] answers){
		this.answers = answers;
	}
	
	public String ask(String question){
		return answers[position++];
	}

}