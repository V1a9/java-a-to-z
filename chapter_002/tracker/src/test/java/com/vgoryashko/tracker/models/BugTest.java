package com.vgoryashko.tracker.models;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Class for testing Bug class.
 * @author Vlad Goryashko
 * @version 1.0
 * @since 11.21.2016
 */
public class BugTest {

    /**
     * Method for testing Bug class.
     */
    @Test
    public void whenNewBugInstantiatedThanObjectIsNotNull() {
        assertNotNull(new Bug());
    }
}
