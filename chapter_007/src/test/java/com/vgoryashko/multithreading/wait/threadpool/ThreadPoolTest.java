package com.vgoryashko.multithreading.wait.threadpool;

import org.junit.Before;
import org.junit.Test;

/**
 * Class that todo.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/26/17
 */
public class ThreadPoolTest {

    /**
     * todo.
     */
    @Before
    public void setUp() {
    }

    /**
     * todo.
     */
    @Test
    public void testEnv() {

        ThreadPool threadPool = new ThreadPool();

        Work[] works = new Work[6];

        works[0] = new Work(10);
        works[1] = new Work(10);
        works[2] = new Work(10);
        works[3] = new Work(10);
        works[4] = new Work(10);
        works[5] = new Work(10);

        for (int j = 0; j < 6; j++) {

            threadPool.add(works[j]);

        }

        threadPool.stopAllThreads();
    }
}