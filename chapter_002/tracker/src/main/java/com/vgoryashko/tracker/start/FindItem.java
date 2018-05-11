package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of searching a request in the system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class FindItem extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public FindItem(String aName) {
        super(aName);
    }

    /**
     * Variable that holds key value for find item action.
     */
    private final int findItemKey = 5;

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return findItemKey;
    }

    /**
     * Method that executes searching of a request.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        TrackerMenu trackerMenu = new TrackerMenu(aTracker, aInput);
        do {
            trackerMenu.showActionsFindItem();
            int key = Integer.valueOf(aInput.ask("\nSelect: "));
            trackerMenu.selectActionsFindItem(key);
        } while (!"y".equals(aInput.ask("Exit?: y/n")));
    }
}
