package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of searching a request by an Id in the system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class FindById extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public FindById(String aName) {
        super(aName);
    }

    /**
     * Variable that holds key value for find item by id action.
     */
    private final int findItemByIdKey = 2;

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return findItemByIdKey;
    }

    /**
     * Method that executes searching of a request by Id.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String id = aInput.ask("\nPlease enter name Item's Id to be found: ");
        try {
            System.out.printf("\nName: %s, Id: %s, Description: %s", aTracker.findById(id).getName(), id, aTracker.findById(id).getDescription());
        } catch (InvalidRequestException ire) {
            System.out.println("\nPlease enter a valid request's id: ");
        }
    }
}
