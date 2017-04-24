package com.vgoryashko.tracker.start;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that implements UI for operating with the tracking system.
 * @author Vlad Goryashko
 * @version 3.1
 * @since 24/04/2017
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
     * Variable that holds array of actions that can be performed with Items in the system from main menu.
     */
    private List<UserAction> actionsMain = new ArrayList<>();

    /**
     * Variable that holds array of actions that allows an user to add Item, Task or Bug to the system.
     */
    private List<UserAction> actionsCreateItem = new ArrayList<>();

    /**
     * Variable that holds array of actions that allows an user to find an Item by name or ID.
     */
    private List<UserAction> actionsFindItem = new ArrayList<>();

    /**
     * Variable that holds array of actions that allows an user to replace an Item.
     */
    private List<UserAction> actionsReplaceItem = new ArrayList<>();

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
     * Method that fills into variable actionsMain all the actions that can be performed with the system from main menu.
     */
    public void fillActionsMain() {

        UserAction[] actions = new UserAction[]{
                new AddNewRequest("Add a new request, please choose a type of request in the next menu;"),
                new AddComment("Add a new comment;"),
                new ShowItems("Show all items;"),
                new RemoveItem("Remove Item;"),
                new FindItem("Find Item;"),
                new ReplaceItem("Replace Item (check Id first);")
        };

        Collections.addAll(this.actionsMain, actions);

    }

    /**
     * Method that fills into variable actionsCreateItem all actions that can be performed while adding Item, Task or Bug.
     */
    public void fillActionsCreateItem() {
        UserAction[] actions = new UserAction[]{
                new AddItem("Add a new Item;"),
                new AddTask("Add a new Task;"),
                new AddBug("Add a new Bug;")
        };

        Collections.addAll(this.actionsCreateItem, actions);
    }

    /**
     * Method that fills into variable actionsFindItem all actions that allows searching items in the system.
     */
    public void fillActionsFindItem() {
        UserAction[] actions = new UserAction[]{
                new FindByName("Find Item by name;"),
                new FindById("Find Item by Id;")
        };

        Collections.addAll(this.actionsFindItem, actions);
    }

    /**
     * Method that fills into variable actionsReplaceItem all actions that related to replacement of items in the system.
     */
    public void fillActionsReplaceItem() {
        UserAction[] actions = new UserAction[]{
                new ReplaceByItem("Replace by Item;"),
                new ReplaceByTask("Replace by Task;"),
                new ReplaceByBug("Replace by Bug;")
        };

        Collections.addAll(this.actionsReplaceItem, actions);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed on Items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsMain(int key) {
        this.actionsMain.get(key - 1).execute(this.input, this.tracker);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed while creating Items, Tasks or Bugs.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsCreateItem(int key) {
        this.actionsCreateItem.get(key - 1).execute(this.input, this.tracker);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed while searching items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsFindItem(int key) {
        this.actionsFindItem.get(key - 1).execute(this.input, this.tracker);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed while replacing items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsReplaceItem(int key) {
        this.actionsReplaceItem.get(key - 1).execute(this.input, this.tracker);
    }

    /**
     * Method that depicts main menu of the tracking system.
     */
    public void showActionsMain() {
        for (UserAction action : this.actionsMain) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Method that depicts create item menu of the tracking system.
     */
    public void showActionsCreateItem() {
        for (UserAction action : this.actionsCreateItem) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Method that depicts replace item menu of the tracking system.
     */
    public void showActionsReplaceItem() {
        for (UserAction action : this.actionsReplaceItem) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Method that depicts find item menu of the tracking system.
     */
    public void showActionsFindItem() {
        for (UserAction action : this.actionsFindItem) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}