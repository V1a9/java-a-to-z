package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Item;

/**
 * Class class that implements user action of depicting all items entered into the system.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 06/12/2016
 */
public class ShowItems extends BaseAction {

    /**
     * Variable that is used for operating with the class Tracker.
     */
    Tracker tracker = new Tracker();
    /**
     * Variable that is used for implementation of a number different types of input methods.
     */
    Input input = new ConsoleInput();

    public ShowItems(String aName) {
        super(aName);
    }

    /**
     * Method that indicate a key of action that an user wants to perform with Item.
     * @return                          <code>int</code>
     */
    public int key() {
        return 3;
    }

    /**
     * Method that executes depicting of all requests.
     * @param aInput							an object that implements Input interface for realization
     *                                          of interaction with an end user.
     * @param aTracker							an object that implements Tracking system.
     */
     public void execute(Input aInput, Tracker aTracker) {
         for (Item item : aTracker.getAll()) {
             if (item.getComment() == null) {
                 System.out.printf("\nName: %s, Description: %s, Id: %s.", item.getName(), item.getDescription(), item.getId());
             } else {
                 System.out.printf("\nName: %s, Description: %s, Id: %s, Comment: %s.", item.getName(), item.getDescription(), item.getId(), item.getComment().getCommentField());
             }
         }
     }
}
