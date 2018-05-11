package com.vgoryashko.tracker.start;

/**
 * Interface that groups all methods required for operating with the tracking system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 29/11/2016
 */
public interface UserAction {

    /**
     * Method that indicate an action that an user want to perform with Items.
     * @return                          <code>int</code>
     */
    int key();

    /**
     * Method that executes an action with Items.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    void execute(Input aInput, Tracker aTracker);

    /**
     * Method that depicts an info about action that is being performed.
     * @return                          String that depicts an info about action
     */
    String info();
}
