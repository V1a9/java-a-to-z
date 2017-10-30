package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Bug;

/**
 * Class class that implements user action of adding a new Bug into the system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class AddBug extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public AddBug(String aName) {
        super(aName);
    }

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return 3;
    }

    /**
     * Method that executes adding of Item.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String name = aInput.ask("\nPlease enter Bug name: ");
        String desc = aInput.ask("\nPlease enter Bug description: ");
        aTracker.addItem(new Bug(name, desc));
    }
}
