package com.vgoryashko.multithreading.wait.threadpool;

import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Class that that implement Thread Pool.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 9/29/17
 */
@ThreadSafe
public class ThreadPool {

    /**
     * Variable that defines a number of available CPUs in a system.
     */
    private int cores = Runtime.getRuntime().availableProcessors();

    /**
     * Variable that refers to a queue of Works to be executed..
     */
    private final Queue<Work> queue;

    /**
     * An array of threads that will run Works.
     */
    private PoolThread[] threads = new PoolThread[this.cores];

    /**
     * Constructor for the class.
     */
    public ThreadPool() {

        this.queue  = new ArrayBlockingQueue<>(6, true);

        for (int i = 0; i < cores; i++) {
            threads[i] = new PoolThread();
            threads[i].start();
        }

    }

    /**
     * Method that add an work to the queue of Works and notified threads that a new Work is available.
     *
     * @param work to be performed by ThreadPool
     */
    public void add(Work work) {

        synchronized (this.queue) {

            this.queue.add(work);
            this.queue.notifyAll();

        }
    }

    /**
     * Class that defines Thread that runs in the pool.
     */
    private class PoolThread extends Thread {

        /**
         * Flag that indicates whether threads can keep running or they have to stop.
         */
        private boolean stop = false;

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            Work work;

            while (!this.stop) {

                synchronized (queue) {

                    while (queue.isEmpty()) {

                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            return;
                        }

                    }

                    work = queue.poll();

                    if (work != null) {

                        work.run();

                    }

                }

            }
        }

        /**
         * Method that notifies a thread that it have to stop.
         */
        public synchronized void stopThread() {
            this.interrupt();
            this.stop = true;
        }
    }

    /**
     * Method that stops all threads.
     */
    public void stopAllThreads() {

        for (PoolThread thread : threads) {
            thread.stopThread();
        }
    }
}
