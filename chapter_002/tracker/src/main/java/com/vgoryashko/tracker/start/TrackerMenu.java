package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Bug;
import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;

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
                                            new RemoveItem(),
                                            new FindItem(),
                                            new ReplaceItem()};
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
        this.actionsFindItem = new UserAction[]{new FindByName(),
                                                new FindById()};
    }

    /**
     * Method that fills into variable actionsReplaceItem all actions that related to replacement of items in the system.
     */
    public void fillActionsReplaceItem() {
        this.actionsReplaceItem = new UserAction[]{new ReplaceByItem(),
                                                    new ReplaceByTask(),
                                                    new ReplaceByBug()};
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

    /**
     * Inner class that implements user action of removing an item from the system.
     */
    private class RemoveItem implements UserAction {

        /**
         * Variable that holds key value for remove item action.
         */
        private final int removeItemKey = 4;

        /**
         * Method that indicate a key of action that an user wants to perform with Item.
         * @return                          <code>int</code>
         */
        public int key() {
            return removeItemKey;
        }

        /**
         * Method that executes removing of a request.
         * @param aInput							an object that implements Input interface for realization
         *                                          of interaction with an end user.
         * @param aTracker							an object that implements Tracking system.
         */
        public void execute(Input aInput, Tracker aTracker) {
            String id = aInput.ask("\nPlease enter Item's Id to be removed: ");
            try {
                aTracker.removeItem(id);
            } catch (InvalidRequestException ire) {
                System.out.println("\nThere is no a request with such id.");
            }
        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Remove Item;");
        }
    }

    /**
     * Inner class that implements user action of searching an item in the system (includes sub-menu for this action).
     */
    private class FindItem implements UserAction {

        /**
         * Variable that holds key value for find item action.
         */
        private final int findItemKey = 5;

        /**
         * Method that indicate a key of action that an user wants to perform with Item.
         * @return                          <code>int</code>
         */
        public int key() {
            return findItemKey;
        }

        /**
         * Method that executes searching of a request.
         * @param aInput							an object that implements Input interface for realization
         *                                          of interaction with an end user.
         * @param aTracker							an object that implements Tracking system.
         */
        public void execute(Input aInput, Tracker aTracker) {
            do {
                TrackerMenu.this.showActionsFindItem();
                int key = Integer.valueOf(aInput.ask("\nSelect: "));
                TrackerMenu.this.selectActionsFindItem(key);
            } while (!"y".equals(aInput.ask("Exit?: y/n")));
        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find Item, please choose an option in the next menu (check name and Id first);");
        }
    }

    /**
     * Inner class that implements user action of searching an item by a name in the system.
     */
    private class FindByName implements UserAction {

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
            String name = input.ask("\nPlease enter name Item's name to be found: ");
            try {
                System.out.printf("\nName: %s, Id: %s, Description: %s", name, tracker.findByName(name).getId(), tracker.findByName(name).getDescription());

            } catch (InvalidRequestException ire) {
                System.out.println("\nPlease enter a valid request's name: ");
            }

        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by name;");
        }
    }

    /**
     * Inner class that implements user action of searching an item by an Id in the system.
     */
    private class FindById implements UserAction {

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

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by Id;");
        }
    }


    /**
     * Inner class that implements user action of replacing an item in the system (includes depicting of sub-menu for the action).
     */
    private class ReplaceItem implements UserAction {

        /**
         * Variable that holds key value for replace item action.
         */
        private final int replaceItemKey = 6;

        /**
         * Method that indicate a key of action that an user wants to perform with Item.
         * @return                          <code>int</code>
         */
        public int key() {
            return replaceItemKey;
        }

        /**
         * Method that implements entering into menu for replacing a request.
         * @param aInput							an object that implements Input interface for realization
         *                                          of interaction with an end user.
         * @param aTracker							an object that implements Tracking system.
         */
        public void execute(Input aInput, Tracker aTracker) {
            do {
                TrackerMenu.this.showActionsReplaceItem();
                int key = Integer.valueOf(aInput.ask("\nSelect: "));
                TrackerMenu.this.showActionsReplaceItem();
            } while (!"y".equals(aInput.ask("Exit?: y/n")));
        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Replace Item (check Id first);");
        }
    }

    /**
     * Inner class that implements user action of replacing a request by an item in the system.
     */
    private class ReplaceByItem implements UserAction {

        /**
         * Method that indicate a key of action that an user wants to perform with Item.
         * @return                          <code>int</code>
         */
        public int key() {
            return 1;
        }

        /**
         * Method that implements entering into menu for replacing a request by item.
         * @param aInput							an object that implements Input interface for realization
         *                                          of interaction with an end user.
         * @param aTracker							an object that implements Tracking system.
         */
        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Item's name: ");
            String desc = aInput.ask("\nPlease enter Item's description: ");
            String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
            try {
                aTracker.findById(id);
                Item item = new Item(name, desc);
                item.setId(id);
                aTracker.replace(item);
            } catch (InvalidRequestException ire) {
                System.out.println("\nThere is no a request with such id.");
            }
        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Replace by Item;");
        }
    }

    /**
     * Inner class that implements user action of replacing a request by a task in the system.
     */
    private class ReplaceByTask implements UserAction {

        /**
         * Method that indicate a key of action that an user wants to perform with Item.
         * @return                          <code>int</code>
         */
        public int key() {
            return 2;
        }

        /**
         * Method that implements entering into menu for replacing a request by task.
         * @param aInput							an object that implements Input interface for realization
         *                                          of interaction with an end user.
         * @param aTracker							an object that implements Tracking system.
         */
        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Task's name: ");
            String desc = aInput.ask("\nPlease enter Task's description: ");
            String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
            try {
                aTracker.findById(id);
                Item item = new Task(name, desc);
                item.setId(id);
                aTracker.replace(item);
            } catch (InvalidRequestException ire) {
                System.out.println("\nThere is no a request with such id.");
            }
        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Replace by Task;");
        }
    }

    /**
     * Inner class that implements user action of replacing a request by a bug in the system.
     */
    private class ReplaceByBug implements UserAction {

        /**
         * Variable that holds key value for replace item by bug action.
         */
        private final int replaceItemByBugKey = 3;

        /**
         * Method that indicate a key of action that an user wants to perform with Item.
         * @return                          <code>int</code>
         */
        public int key() {
            return replaceItemByBugKey;
        }

        /**
         * Method that implements entering into menu for replacing a request by task.
         * @param aInput							an object that implements Input interface for realization
         *                                          of interaction with an end user.
         * @param aTracker							an object that implements Tracking system.
         */
        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Bug's name: ");
            String desc = aInput.ask("\nPlease enter Bug's description: ");
            String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
            try {
                aTracker.findById(id);
                Item item = new Bug(name, desc);
                item.setId(id);
                aTracker.replace(item);
            } catch (InvalidRequestException ire) {
                System.out.println("\nThere is no a request with such id.");
            }
        }

        /**
         * Method that depicts an info about action that is being performed.
         * @return                          String that depicts an info about action
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Replace by Bug;");
        }
    }
}