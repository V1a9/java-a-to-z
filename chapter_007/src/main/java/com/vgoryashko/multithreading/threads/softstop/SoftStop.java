package com.vgoryashko.multithreading.threads.softstop;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Class that calculates chars in a file and auto terminates after a given time set by a user.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/9/17
 */
public class SoftStop {

    /**
     * Variable that stores a result after calculations of chars in a file.
     */
    private long result;

    /**
     * Variable that refers to a file to be processed.
     */
    private Path path;

    /**
     * Variable that refers to an instance of Thread to be stopped.
     */
    private Thread watchThread;

    /**
     * Variable that stores time that application should execute processing of a file.
     */
    private long time;

    /**
     * Constructor for the class.
     *
     * @param path to file to be processed
     * @param time set to process file
     */
    public SoftStop(Path path, long time) {

        this.path = path;
        this.time = time;

    }

    /**
     * Class that implements timer.
     */
    private class Time implements Runnable {

        /**
         * Variable that refers to an instance of Thread.
         */
        private Thread timeThread;

        /**
         * Variable that stores start time of application.
         */
        private long startTime = System.currentTimeMillis();

        /**
         * Constructor of the class.
         */
        Time() {

            timeThread = new Thread(this);

        }

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

            while (true) {

                long currentDuration = System.currentTimeMillis() - startTime;

                if (currentDuration > time) {

                    watchThread.interrupt();
                    break;

                }
            }
        }
    }

    /**
     * Class that calculates symbols in a give file.
     */
    private class CountChars implements Runnable {

        /**
         * Variable that refers to an instance of a Thread.
         */
        private Thread countCharsThread;

        /**
         * Constructor of the class.
         */
        CountChars() {

            this.countCharsThread = new Thread(this);
            watchThread = this.countCharsThread;

        }


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

            try (FileReader reader = new FileReader(path.toString())) {

                int charRead;

                while (!this.countCharsThread.isInterrupted()) {

                    charRead = reader.read();

                    if (charRead == -1) {

                        break;

                    } else {

                        ++result;

                    }

                }

            } catch (IOException ioe) {

                ioe.printStackTrace();

            }
        }
    }

    /**
     * Method that implements main logic.
     *
     * @return long qty of chars in a file
     * @throws Exception Exception
     */
    public long calculate() throws Exception {

        Time time = new Time();
        CountChars countChars = new CountChars();

        time.timeThread.start();
        countChars.countCharsThread.start();
        countChars.countCharsThread.join();

        return this.result;

    }
}
