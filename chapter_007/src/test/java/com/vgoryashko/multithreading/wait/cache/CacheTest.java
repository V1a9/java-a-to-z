package com.vgoryashko.multithreading.wait.cache;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests implementation of the Cache class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/22/17
 */
public class CacheTest {

    /**
     * Variable that refers to an instance of Cache.
     */
    private Cache<String, Integer> cache;

    /**
     * Method that initializes test environments.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {

        cache = new Cache<>();

        cache.add("K1", 1);
        cache.add("K2", 2);
        cache.add("K3", 3);

    }

    /**
     * Method that tests add method.
     * @throws Exception Exception
     */
    @Test
    public void whenAddInvokedThenElementAdded() throws Exception {
        assertThat(cache.getCacheMap().size(), is(3));
    }

    /**
     * Method that tests delete method.
     * @throws Exception Exception
     */
    @Test
    public void whenDeleteInvokedThenElementDeleted() throws Exception {
        cache.delete("K2");
    }

    /**
     * Method that tests update method.
     * @throws Exception Exception
     */
    @Test
    public void whenUpdateInvokedThenAnElementUpdated() throws Exception {

        Thread t1 = new Thread("Thread 1") {

            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             */
            @Override
            public void run() {

                Model<String, Integer> current = cache.getCacheMap().get("K2");
                cache.update(current.getName(), current.getValue() + 2, current.getVersion());

            }
        };

        Thread t2 = new Thread("Thread 2") {

            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             */
            @Override
            public void run() {

                Model<String, Integer> current = cache.getCacheMap().get("K2");

                int currentVersion = current.getVersion();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cache.update(current.getName(), current.getValue() + 3, currentVersion);

            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertThat(this.cache.getCacheMap().get("K2").getVersion(), is(1));
        assertThat(this.cache.getCacheMap().get("K2").getValue(), is(4));
    }
}