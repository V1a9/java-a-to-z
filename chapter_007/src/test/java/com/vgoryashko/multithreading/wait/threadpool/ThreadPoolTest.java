package com.vgoryashko.multithreading.wait.threadpool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests ThreadPool application.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 9/29/17
 */
public class ThreadPoolTest {

    /**
     * Variable that refers to an instance of the ThreadPool.
     */
    private ThreadPool threadPool;

    /**
     * Variable that refers to an array of instances Work.
     */
    private Work[] works;

    /**
     * Array that stores result of works performed.
     */
    private List<Integer> result;

    /**
     * Method that initializes test environments.
     */
    @Before
    public void setUp() {

        this.threadPool = new ThreadPool();
        this.works = new Work[6];
        this.result = new ArrayList<>();

    }

    /**
     * Method that tests ThreadPool.
     */
    @Test
    public void whenThreadPoolStartedThenAllWorkFromStackIsCompleted() {


        works[0] = new Work(10, this.result);
        works[1] = new Work(10, this.result);
        works[2] = new Work(10, this.result);
        works[3] = new Work(10, this.result);
        works[4] = new Work(10, this.result);
        works[5] = new Work(10, this.result);

        for (int j = 0; j < 6; j++) {

            threadPool.add(works[j]);

        }

        threadPool.stopAllThreads();

        for (int i : result) {

            assertThat(i, is(10));

        }
    }
}