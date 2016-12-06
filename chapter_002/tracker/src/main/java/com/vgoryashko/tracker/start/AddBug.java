package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Bug;

/**
 * Class class that implements user action of adding a new Bug into the system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 06/12/2016
 */
public class AddBug extends BaseAction {
    /**
     * Variable that is used for operating with the class Tracker.
     */
    Tracker tracker = new Tracker();
    /**
     * Variable that is used for implementation of a number different types of input methods.
     */
    Input input = new ConsoleInput();
    /**
     * Variable that is used for operating with UI.
     */
    TrackerMenu trackerMenu = new TrackerMenu(this.tracker, this.input);

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
