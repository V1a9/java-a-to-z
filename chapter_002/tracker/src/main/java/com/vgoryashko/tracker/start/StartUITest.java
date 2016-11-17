package com.vgoryashko.tracker.start;

/*
 *Class that tests class
 *@author vgoryashko
 *@version 0.1
 *@since 16/11/2016 
 */

import com.vgoryashko.tracker.models.*;

public class StartUITest {
	public static void main (String[] args){
		Input input = new StubInput(new String[] {"create stub task"});
		new StartUI(input).init();
	}	
}