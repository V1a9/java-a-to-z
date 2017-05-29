package com.vgoryashko.collectionspro.dynamiclist;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Class that tests implementation of dynamic list.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 29.05.2017
 */
public class DynamicListTest {

    /**
     * Variable that is used for referring to a list instance.
     */
    private DynamicList<Integer> list;

    /**
     * Block that initializes test environments.
     */
    @Before
    public void init() {
        list = new DynamicList<>();
    }

    /**
     * Method that tests addition of elements to a Dynamic List.
     */
    @Test
    public void whenElementsAddedTheyArePutIntoList() {

        int[] expected = new int[]{0, 1, 2, 3, 4};

        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        for (int i = 0; i < 5; i++) {
            assertThat(list.get(i), is(expected[i]));
        }
    }

    /**
     * Method that tests extension of a list when a new element is added and its position number exceeds a size of a list.
     */
    @Test
    public void whenElementAddedAndItsPositionExceedsSizeOfListSizeExtends() {

        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100};

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.add(100);

        for (int i = 0; i < 11; i++) {
            assertThat(list.get(i), is(expected[i]));
        }
    }

    /**
     * Method that tests iterator for a List.
     */
    @Test
    public void whenIteratorIsInvokedThenItReturnAllNotNullElements() {

        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iteratorList = list.iterator();

        int index = 0;

        while (iteratorList.hasNext() && index < 10) {
            assertThat(iteratorList.next(), is(expected[index++]));
        }

    }
}