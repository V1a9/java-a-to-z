package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;

/**
 * Class that implements UI for operating with the tracking system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 29/11/2016
 */
public class TrackerMenu {

    /**
     * Variable that is used for operating with the class Tracker.
     */
    private Tracker tracker;

    /**
     * Variable that is used for implementation of a number different types of input methods.
     */
    private Input input;

    /**
     * Variable that holds array of all actions that can be performed with Items in the system from main menu.
     */
    private UserAction[] actionsMain = new UserAction[7];

    /**
     * Variable that holds array of actions that allows an user to add Item, Task or Bug to the system.
     */
    private UserAction[] actionsCreateItem = new UserAction[4];

    /**
     * Variable that holds array of actions that allows an user to find an Item by name or ID.
     */
    private UserAction[] actionsFindItem = new UserAction[3];

    /**
     * Variable that holds array of actions that allows an user to replace an Item.
     */
    private UserAction[] actionsReplaceItem = new UserAction[4];

    /**
     * Constructor for the class.
     * @param aTracker                          an object that implements tracking system
     * @param aInput                            an object that implements Input interface for realization
     *                                          of interaction with an end user
     */
    public TrackerMenu(Tracker aTracker, Input aInput) {
        this.tracker = aTracker;
        this.input = aInput;
    }

    /**
     * Method that contains all the actions that can be performed in the system on Items.
     */
    public void fillActions() {
        this.actionsMain[0] = new AddItem();
    }

    /**
     * Method that selects an action based on a unique key (assigned to each action)
     * to be performed on Items.
     * @param key                               unique value assigned to each action
     */
    public void select(int key) {
        this.actionsMain[key].execute(this.input, this.tracker);
    }

    /**
     * Method that depicts main menu of the tracking system.
     */
    public void showMainMenu() {
        for (UserAction actionsMainMenu : this.actionsMain) {
            if (actionsMainMenu != null) {
                System.out.println(actionsMainMenu.info());
            }
        }
    }

    /**
     * Inner class that implements user action of adding a new Item to the system.
     */
    private class AddItem implements UserAction {

        public int key() {
            return 0;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = input.ask("\nPlease enter Item name");
            String desc = input.ask("\nPlease enter Item description");
            tracker.addItem(new Item(name, desc));
        }

        public String info() {
            return String.format("%s. %s,", this.key(), "Add a new Item: ");
        }
    }
}
