package com.vgoryashko.multithreading.jmm;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class that tests classes that demonstrate Visibility of Shared Objects issue.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/11/17
 */
public class VisibilitySharedObjectsTest {

     /**
     * Method that tests demonstration of Visibility of Shared Objects issue.
     */
    @Test
    public void whenSharedObjectChangedByOneThreadThenThisModificationIsNotSeenByAnotherThread() {

        VisibilitySharedObjects visibilitySharedObjects = new VisibilitySharedObjects();
        assertThat(visibilitySharedObjects.startApp(), is(10));

    }

}