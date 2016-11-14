package com.vgoryashko.tracker.start;

/*
 *Class that implements system that implements UI for tracking system 
 *@author vgoryashko
 *@version 0.2
 *@since 14/11/2016 
 */

import com.vgoryashko.tracker.models.*;

public class StartUI{

	public static void main (String[] args){
		Tracker tracker = new Tracker();
		tracker.addItem(new Task("Task_0", "Desc_0"));
	}
	
}