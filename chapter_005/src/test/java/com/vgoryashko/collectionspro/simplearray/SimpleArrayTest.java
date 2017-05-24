package com.vgoryashko.collectionspro.simplearray;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class that tests implementation of the SimpleArray class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 24.05.2017
 */
public class SimpleArrayTest {


    @Test
    public void whenSimpleArrayOfIntegersCreatedThenOnlyIntegersCanBeUsed() {

        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);

        simpleArray.add(12);
        assertThat(simpleArray.get(0), is(12));

        simpleArray.add(21);
        assertThat(simpleArray.get(1), is(21));

        simpleArray.update(1, 33);
        assertThat(simpleArray.get(1), is(33));

        simpleArray.delete(1);
        assertNull(simpleArray.get(1));
    }
}