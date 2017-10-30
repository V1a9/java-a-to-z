package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of removing a request from the system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class RemoveItem extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public RemoveItem(String aName) {
        super(aName);
    }

    /**
     * Variable that holds key value for remove item action.
     */
    private final int removeItemKey = 4;

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return removeItemKey;
    }

    /**
     * Method that executes removing of a request.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String id = aInput.ask("\nPlease enter Item's Id to be removed: ");
        try {
            aTracker.removeItem(id);
        } catch (InvalidRequestException ire) {
            System.out.println("\nThere is no a request with such id.");
        }
    }
}
