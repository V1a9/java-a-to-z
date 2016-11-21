package com.vgoryashko.tracker.models;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Class for testing Task class.
 * @author Vlad Goryashko
 * @version 1.0
 * @since 11.21.2016
 */
public class TaskTest {

    /**
     * Method for testing Task class.
     */
    @Test
    public void whenNewTaskInstantiatedThanObjectIsNotNull() {
        assertNotNull(new Task("TaskTest", "Task for testing", 0L));
    }
}
