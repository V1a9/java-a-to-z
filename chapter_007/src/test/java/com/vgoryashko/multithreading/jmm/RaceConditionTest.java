package com.vgoryashko.multithreading.jmm;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests classes that demonstrate Race Condition issue.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/11/17
 */
public class RaceConditionTest {

    /**
     * Method that tests demonstration of Visibility of Race Condition issue.
     */
    @Test
    public void whenSharedObjectChangedByBothThreadsAndOnlyOneReturnsValueThenValueIncrementedOnce() {

        RaceCondition raceCondition = new RaceCondition();
        assertThat(raceCondition.startApp(), is(11));

    }

}