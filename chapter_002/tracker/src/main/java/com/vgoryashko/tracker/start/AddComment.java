package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of adding a new Comment into a request.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class AddComment extends BaseAction {
    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public AddComment(String aName) {
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
     * Method that executes adding a Comment to a request.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        String name = aInput.ask("\nPlease enter name Item name where comment has to be added to: ");
        try {
            String comment = aInput.ask("\nPlease enter comment: ");
            aTracker.addComment(name, comment);
        } catch (InvalidRequestException ire) {
            System.out.println("\nThere is no a request with such name.");
        }
    }
}