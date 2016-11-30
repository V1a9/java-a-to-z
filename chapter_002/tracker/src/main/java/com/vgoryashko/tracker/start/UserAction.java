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
     */
    void execute(Input input, Tracker tracker);

    /**
     * Method that depicts an info about action that is being performed.
     */
    String info();
}
