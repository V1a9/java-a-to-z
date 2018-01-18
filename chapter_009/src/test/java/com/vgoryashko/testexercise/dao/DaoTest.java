package com.vgoryashko.testexercise.dao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that test Dao class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/15/18
 */
public class DaoTest {

    private Dao dao;

    @Before
    public void setUp() throws Exception {

        dao = Dao.getInstance();
        dao.setupPool();

    }

    @Test
    public void initTest() throws Exception {

        dao.init();

    }

}