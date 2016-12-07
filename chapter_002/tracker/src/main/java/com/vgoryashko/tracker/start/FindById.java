package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of searching a request by an Id in the system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class FindById extends BaseAction {

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
        String id = input.ask("\nPlease enter name Item's Id to be found: ");
        try {
            System.out.printf("\nName: %s, Id: %s, Description: %s", tracker.findById(id).getName(), id, tracker.findById(id).getDescription());
        } catch (InvalidRequestException ire) {
            System.out.println("\nPlease enter a valid request's id: ");
        }
    }
}
