package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;

import javax.jws.soap.SOAPBinding;

/**
 * Class that implements UI for operating with the tracking system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 29/11/2016
 */
public class TrackerMenu {

    private Tracker tracker;

    private Input input;

    private UserAction[] actions = new UserAction[7];

    public TrackerMenu(Tracker aTracker, Input aInput) {
        this.tracker = aTracker;
        this.input = aInput;
    }

    public void fillActions() {
        this.actions[0] = new AddItem();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

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
