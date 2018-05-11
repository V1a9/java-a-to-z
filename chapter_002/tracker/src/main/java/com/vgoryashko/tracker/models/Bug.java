package com.vgoryashko.tracker.models;

/**
 * Class that derives from Item and describes bugs.
 */

public class Bug extends Item {

    /**
     * Constructor for the class.
     * @param name							A name to be set for a Bug
     * @param desc							A description to be set for a Bug
     */
    public Bug(String name, String desc) {
        super(name, desc);
    }
}