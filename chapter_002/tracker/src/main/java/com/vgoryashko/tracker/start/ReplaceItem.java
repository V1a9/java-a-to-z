package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of replacing a request in the system (including sub-menu).
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class ReplaceItem extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public ReplaceItem(String aName) {
        super(aName);
    }
    /**
     * Variable that holds key value for replace item action.
     */
    private final int replaceItemKey = 6;

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return replaceItemKey;
    }

    /**
     * Method that implements entering into menu for replacing a request.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        TrackerMenu trackerMenu = new TrackerMenu(aTracker, aInput);
        do {
            trackerMenu.showActionsReplaceItem();
            int key = Integer.valueOf(aInput.ask("\nSelect: "));
            trackerMenu.showActionsReplaceItem();
        } while (!"y".equals(aInput.ask("Exit?: y/n")));
    }
}
