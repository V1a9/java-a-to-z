package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of adding a new request (Item, Task or Bug) to the system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class AddNewRequest extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public AddNewRequest(String aName) {
        super(aName);
    }
    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return 1;
    }

    /**
     * Method that selects action based on a key.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        TrackerMenu trackerMenu = new TrackerMenu(aTracker, aInput);
        do {
            trackerMenu.showActionsCreateItem();
            int key = Integer.valueOf(aInput.ask("\nSelect: "));
            trackerMenu.selectActionsCreateItem(key);
        } while (!"y".equals(aInput.ask("\nExit?: y")));
    }
}
