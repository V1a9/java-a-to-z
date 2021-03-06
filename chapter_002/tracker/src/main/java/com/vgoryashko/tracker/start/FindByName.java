package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of searching a request by a name in the system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class FindByName extends BaseAction {

    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public FindByName(String aName) {
        super(aName);
    }

    /**
     * Variable that holds key value for find item by name action.
     */
    private final int findItemByNameKey = 1;

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return findItemByNameKey;
    }

    /**
     * Method that executes searching of a request by name.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String name = aInput.ask("\nPlease enter name Item's name to be found: ");
        try {
            System.out.printf("\nName: %s, Id: %s, Description: %s", name, aTracker.findByName(name).getId(), aTracker.findByName(name).getDescription());
        } catch (InvalidRequestException ire) {
            System.out.println("\nPlease enter a valid request's name: ");
        }

    }
}
