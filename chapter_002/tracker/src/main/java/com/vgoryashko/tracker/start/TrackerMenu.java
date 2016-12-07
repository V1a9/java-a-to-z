package com.vgoryashko.tracker.start;

/**
 * Class that implements UI for operating with the tracking system.
 * @author Vlad Goryashko
 * @version 2.0
 * @since 03/12/2016
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
     * Variable that holds qty of actions in the main menu.
     */
    private final int mainActionsQty = 6;

    /**
     * Variable that holds array of actions that can be performed with Items in the system from main menu.
     */
    private UserAction[] actionsMain = new UserAction[mainActionsQty];

    /**
     * Variable that holds qty of actions in the create menu.
     */
    private final int createActionsQty = 3;

    /**
     * Variable that holds array of actions that allows an user to add Item, Task or Bug to the system.
     */
    private UserAction[] actionsCreateItem = new UserAction[createActionsQty];

    /**
     * Variable that holds qty of actions in the find menu.
     */
    private final int findActionsQty = 2;

    /**
     * Variable that holds array of actions that allows an user to find an Item by name or ID.
     */
    private UserAction[] actionsFindItem = new UserAction[findActionsQty];

    /**
     * Variable that holds qty of actions in the replace menu.
     */
    private final int replaceActionsQty = 3;

    /**
     * Variable that holds array of actions that allows an user to replace an Item.
     */
    private UserAction[] actionsReplaceItem = new UserAction[replaceActionsQty];

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
        this.actionsMain = new UserAction[]{new AddNewRequest("Add a new request, please choose a type of request in the next menu;"),
                                            new AddComment("Add a new comment;"),
                                            new ShowItems("Show all items;"),
                                            new RemoveItem("RemoveItem;"),
                                            new FindItem("Find Item;"),
                                            new ReplaceItem("Replace Item (check Id first);")};
    }

    /**
     * Method that fills into variable actionsCreateItem all actions that can be performed while adding Item, Task or Bug.
     */
    public void fillActionsCreateItem() {
        this.actionsCreateItem = new UserAction[]{new AddItem("Add a new Item;"),
                                                    new AddTask("Add a new Task;"),
                                                    new AddBug("Add a new Bug;")};
    }

    /**
     * Method that fills into variable actionsFindItem all actions that allows searching items in the system.
     */
    public void fillActionsFindItem() {
        this.actionsFindItem = new UserAction[]{new FindByName("Find Item by name;"),
                                                new FindById("Find Item by Id;")};
    }

    /**
     * Method that fills into variable actionsReplaceItem all actions that related to replacement of items in the system.
     */
    public void fillActionsReplaceItem() {
        this.actionsReplaceItem = new UserAction[]{new ReplaceByItem("Replace by Item;"),
                                                    new ReplaceByTask("Replace by Task;"),
                                                    new ReplaceByBug("Replace by Bug;")};
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed on Items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsMain(int key) {
        this.actionsMain[key - 1].execute(this.input, this.tracker);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed while creating Items, Tasks or Bugs.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsCreateItem(int key) {
        this.actionsCreateItem[key - 1].execute(this.input, this.tracker);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed while searching items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsFindItem(int key) {
        this.actionsFindItem[key - 1].execute(this.input, this.tracker);
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed while replacing items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsReplaceItem(int key) {
        this.actionsReplaceItem[key - 1].execute(this.input, this.tracker);
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