package com.vgoryashko.tracker.start;

/**
 * Abstract class that defines common methods for user actions via UI.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 06/12/2016
 */
public abstract class  BaseAction implements UserAction {

    /**
     * Variable that holds name of user action.
     */
    private String name;
    /**
     * Constructor for the class.
     * @param aName                             represents name for an action
     */
    public BaseAction(String aName) {
        this.name = aName;
    }

    /**
     * Method that indicate an action that an user want to perform with Items.
     * @return                          <code>int</code>
     */
    public abstract int key();

    /**
     * Method that executes an action with Items.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public abstract void execute(Input aInput, Tracker aTracker);

    /**
     * Method that depicts an info about action that is being performed.
     * @return                          String that depicts an info about action
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}
