package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Bug;
import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;

/**
 * Class that implements UI for operating with the tracking system.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 01/12/2016
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
    private UserAction[] actionsMain = new UserAction[6];

    /**
     * Variable that holds array of actions that allows an user to add Item, Task or Bug to the system.
     */
    private UserAction[] actionsCreateItem = new UserAction[3];

    /**
     * Variable that holds array of actions that allows an user to find an Item by name or ID.
     */
    private UserAction[] actionsFindItem = new UserAction[2];

    /**
     * Variable that holds array of actions that allows an user to replace an Item.
     */
    private UserAction[] actionsReplaceItem = new UserAction[3];

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
    public void fillActionsMain() {
        this.actionsMain[0] = new AddNewRequest();
        this.actionsMain[1] = new AddComment();
        this.actionsMain[2] = new ShowItems();
        this.actionsMain[3] = new RemoveItem();
        this.actionsMain[4] = new FindItem();
        this.actionsMain[5] = new ReplaceItem();
    }

    public void fillActionsCreateItem() {
        this.actionsCreateItem[0] = new AddItem();
        this.actionsCreateItem[1] = new AddTask();
        this.actionsCreateItem[2] = new AddBug();
    }

    public void fillActionsFindItem() {
        this.actionsFindItem[0] = new FindByName();
        this.actionsFindItem[1] = new FindById();
    }

    public void fillActionsReplaceItem() {
        this.actionsReplaceItem[0] = new ReplaceByItem();
        this.actionsReplaceItem[1] = new ReplaceByTask();
        this.actionsReplaceItem[2] = new ReplaceByBug();
    }

    /**
     * Method that selects an action based on a key (assigned to each action)
     * to be performed on Items.
     * @param key                               value assigned to each action in menu where it's located
     */
    public void selectActionsMain(int key) {
        this.actionsMain[key - 1].execute(this.input, this.tracker);
    }

    public void selectActionsCreateItem(int key) {
        this.actionsCreateItem[key - 1].execute(this.input, this.tracker);
    }

    public void selectActionsFindItem(int key) {
        this.actionsFindItem[key - 1].execute(this.input, this.tracker);
    }

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
     * Method that depicts find item menu of the tracking system.
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
     * Inner class that implements user action of adding a new Item to the system.
     */
    private class AddItem implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Item name: ");
            String desc = aInput.ask("\nPlease enter Item description: ");
            aTracker.addItem(new Item(name, desc));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add a new Item;");
        }
    }

    /**
     * Inner class that implements user action of adding a new Item to the system.
     */
    private class AddNewRequest implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input aInput, Tracker aTracker) {
            do {
                TrackerMenu.this.showActionsCreateItem();
                int key = Integer.valueOf(input.ask("\nSelect: "));
                TrackerMenu.this.selectActionsCreateItem(key);
            } while (!"y".equals(aInput.ask("\nExit?: y")));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add a new request, please choose a type of request in the next menu;");
        }

    }

    private class AddTask implements UserAction{
        public int key() {
            return 2;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Task name: ");
            String desc = aInput.ask("\nPlease enter Task description: ");
            aTracker.addItem(new Task(name, desc));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add a new Task;");
        }
    }

    private class AddBug implements UserAction{
        public int key() {
            return 3;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Bug name: ");
            String desc = aInput.ask("\nPlease enter Bug description: ");
            aTracker.addItem(new Bug(name, desc));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add a new Bug;");
        }
    }

    private class AddComment implements UserAction {

        public int key() {
            return 2;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter name Item name where comment has to be added to: ");
            String comment = aInput.ask("\nPlease enter comment: ");
			aTracker.addComment(name, comment);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add a new comment;");
        }
    }

    private class ShowItems implements UserAction {

        public int key() {
            return 3;
        }

        public void execute(Input aInput, Tracker aTracker) {
            for (Item item : aTracker.getAll()) {
                if (item.getComment() == null) {
                    System.out.printf("\nName: %s, Id: %s.", item.getName(), item.getId());
                } else {
                    System.out.printf("\nName: %s, Id: %s, Comment: %s.", item.getName(), item.getId(), item.getComment().getCommentField());
                }
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all items;");
        }
    }

    private class RemoveItem implements UserAction {

        public int key() {
            return 4;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String id = aInput.ask("\nPlease enter Item's Id to be removed: ");
            aTracker.removeItem(id);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Remove Item;");
        }
    }

    private class FindItem implements UserAction {

        public int key() {
            return 5;
        }

        public void execute(Input aInput, Tracker aTracker) {
            do {
                TrackerMenu.this.showActionsFindItem();
                int key = Integer.valueOf(aInput.ask("\nSelect: "));
                TrackerMenu.this.selectActionsFindItem(key);
            } while (!"y".equals(aInput.ask("Exit?: y/n")));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find Item, please choose an option in the next menu (check name and Id first);");
        }
    }

    private class FindByName implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = input.ask("\nPlease enter name Item's name to be found: ");
            System.out.printf("\nName: %s, Id: %s, Description: %s", name, tracker.findByName(name).getId(), tracker.findByName(name).getDescription());
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by name;");
        }
    }

    private class FindById implements UserAction {

        public int key() {
            return 2;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String id = input.ask("\nPlease enter name Item's Id to be found: ");
			System.out.printf("\nName: %s, Id: %s, Description: %s", tracker.findById(id).getName(), id, tracker.findById(id).getDescription());
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by Id;");
        }
    }

    private class ReplaceItem implements UserAction {

        public int key() {
            return 6;
        }

        public void execute(Input aInput, Tracker aTracker) {
            do {
                TrackerMenu.this.showActionsReplaceItem();
                int key = Integer.valueOf(aInput.ask("\nSelect: "));
                TrackerMenu.this.showActionsReplaceItem();
            } while (!"y".equals(aInput.ask("Exit?: y/n")));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Replace Item (check Id first);");
        }
    }

    private class ReplaceByItem implements UserAction{

        public int key() {
            return 1;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Item's name: ");
            String desc = aInput.ask("\nPlease enter Item's description: ");
			String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
            Item item = new Item(name, desc);
            item.setId(id);
            aTracker.replace(item);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Replace by Item;");
        }
    }

    private class ReplaceByTask implements UserAction{

        public int key() {
            return 2;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Task's name: ");
            String desc = aInput.ask("\nPlease enter Task's description: ");
            String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
            Item item = new Task(name, desc);
            item.setId(id);
            aTracker.replace(item);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Replace by Task;");
        }
    }

    private class ReplaceByBug implements UserAction{

        public int key() {
            return 3;
        }

        public void execute(Input aInput, Tracker aTracker) {
            String name = aInput.ask("\nPlease enter Bug's name: ");
            String desc = aInput.ask("\nPlease enter Bug's description: ");
            String id = aInput.ask("\nPlease enter Id of Item to be replaced: ");
            Item item = new Bug(name, desc);
            item.setId(id);
            aTracker.replace(item);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Replace by Bug;");
        }
    }
}
