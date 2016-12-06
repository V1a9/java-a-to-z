package com.vgoryashko.tracker.start;

/**
 * Class class that implements user action of adding a new request (Item, Task or Bug) to the system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 06/12/2016
 */
public class AddNewRequest extends BaseAction {

    /**
     * Variable that is used for operating with the class Tracker.
     */
    Tracker tracker = new Tracker();
    /**
     * Variable that is used for implementation of a number different types of input methods.
     */
    Input input = new ConsoleInput();
    /**
     * Variable that is used for operating with UI.
     */
    TrackerMenu trackerMenu = new TrackerMenu(this.tracker, this.input);

    public AddNewRequest(String aName) {
        super(aName);
    }

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return 1;
    }

    /**
     * Method that selects action based on a key.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
    public void execute(Input aInput, Tracker aTracker) {
        do {
            this.trackerMenu.showActionsCreateItem();
            int key = Integer.valueOf(input.ask("\nSelect: "));
            this.trackerMenu.selectActionsCreateItem(key);
        } while (!"y".equals(aInput.ask("\nExit?: y")));
    }
}
