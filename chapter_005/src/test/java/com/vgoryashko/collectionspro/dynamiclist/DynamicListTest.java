package com.vgoryashko.collectionspro.dynamiclist;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that tests implementation of dynamic list.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 28.05.2017
 */
public class DynamicListTest {

    @Test
    public void test() {

        DynamicList<Integer> list = new DynamicList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.add(100);

    }
}