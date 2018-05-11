package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;

/**
 * Class class that implements user action of replacing a request by a Task in the system (including sub-menu).
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class ReplaceByTask extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public ReplaceByTask(String aName) {
        super(aName);
    }

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return 2;
    }

    /**
     * Method that implements entering into menu for replacing a request by task.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String name = aInput.ask("\nPlease enter Task's name: ");
        String desc = aInput.ask("\nPlease enter Task's description: ");
        String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
        try {
            aTracker.findById(id);
            Item item = new Task(name, desc);
            item.setId(id);
            aTracker.replace(item);
        } catch (InvalidRequestException ire) {
            System.out.println("\nThere is no a request with such id.");
        }
    }
}
