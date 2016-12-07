package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of replacing a request in the system (including sub-menu).
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class ReplaceItem extends BaseAction {

    /**
     * Variable that is used for operating with the class Tracker.
     */
    private Tracker tracker = new Tracker();
    /**
     * Variable that is used for implementation of a number different types of input methods.
     */
    private Input input = new ConsoleInput();
    /**
     * Variable that is used for operating with UI.
     */
    private TrackerMenu trackerMenu = new TrackerMenu(this.tracker, this.input);

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
        do {
            trackerMenu.showActionsReplaceItem();
            int key = Integer.valueOf(aInput.ask("\nSelect: "));
            trackerMenu.showActionsReplaceItem();
        } while (!"y".equals(aInput.ask("Exit?: y/n")));
    }
}
