package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Bug;

/**
 * Class class that implements user action of replacing a request by a Bug in the system (including sub-menu).
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class ReplaceByBug extends BaseAction {
    /**
     * Variable that is used for operating with the class Tracker.
     */
    private Tracker tracker = new Tracker();
    /**
     * Variable that is used for implementation of a number different types of input methods.
     */
    private Input input = new ConsoleInput();
    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public ReplaceByBug(String aName) {
        super(aName);
    }
    /**
     * Variable that holds key value for replace item by bug action.
     */
    private final int replaceItemByBugKey = 3;

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return replaceItemByBugKey;
    }

    /**
     * Method that implements entering into menu for replacing a request by task.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String name = aInput.ask("\nPlease enter Bug's name: ");
        String desc = aInput.ask("\nPlease enter Bug's description: ");
        String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
        try {
            aTracker.findById(id);
            Item item = new Bug(name, desc);
            item.setId(id);
            aTracker.replace(item);
        } catch (InvalidRequestException ire) {
            System.out.println("\nThere is no a request with such id.");
        }
    }
}
