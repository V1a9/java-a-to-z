package com.vgoryashko.multithreading.wait.threadpool;

import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Class that that implement Thread Pool.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/26/17
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
                            e.printStackTrace();
                        }

                        if (this.stop) {
                            break;
                        }

                    }

                    work = queue.poll();
                    work.run();

                }

            }

        }

        /**
         * Method that changes the flag that signals that thr
         */
        public synchronized void stopThread() {
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

    public static void main(String[] args) {

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

        if (threadPool.queue.isEmpty()) {

            threadPool.stopAllThreads();

        }

    }
}
