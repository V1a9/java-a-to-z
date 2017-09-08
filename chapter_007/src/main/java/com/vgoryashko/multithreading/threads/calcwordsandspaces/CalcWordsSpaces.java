package com.vgoryashko.multithreading.threads.calcwordsandspaces;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Class that implements calculation of words and spaces in a given file concurrently.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 9/08/17
 */
public class CalcWordsSpaces {


    /**
     * Variable that refers to a file to be processed.
     */
    private Path path;

    /**
     * Variable that stores results of calculations.
     */
    private int[] result;

    /**
     * Constant that holds value of 1s.
     */
    private static final int DURATION = 1000;

    /**
     * Variable that stores value for thread termination key.
     */
    private boolean stop = false;

    /**
     * Constructor for the class.
     * @param path to a file to be processed.
     */
    public CalcWordsSpaces(Path path) {

        this.path = path;
        this.result  = new int[2];

    }

    /**
     * Method that implements counter of time a thread is running and interrupts it in case time is exceeds 1 sec.
     */
    private class Counter implements Runnable {

        /**
         * Variable that refers to an instance of a Thread.
         */
        private Thread threadCounter;

        /**
         * Variable that stores a program start time.
         */
        long startTime = System.currentTimeMillis();

        /**
         * Constructor for the class.
         */
        Counter() {

            threadCounter = new Thread(this);

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

            while (((System.currentTimeMillis()) - startTime) > DURATION) {

                stop = true;

            }

        }
    }

    /**
     * Class that calculates spaces in a given file.
     */
    private class CalcSpaces implements Runnable {

        /**
         * Variable that refers to an instance of the Thread.
         */
        private Thread threadSpaces;

        /**
         * Constructor for the class.
         */
        CalcSpaces() {

            threadSpaces = new Thread(this);

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

                while (!stop) {

                    while ((charRead = reader.read()) != -1) {

                        if (0x0020 == charRead) {

                            result[1] = ++result[1];

                        }

                    }

                }

                if (stop) {

                    this.threadSpaces.interrupt();
                    System.out.println("threadSpaces interrupted");

                }


            } catch (IOException ioe) {

                ioe.printStackTrace();

            }

        }

    }

    /**
     * Class that calculates words in a given file.
     */
    private final class CalcWords implements Runnable {

        /**
         * Variable that refers to an instance of the Thread.
         */
        private Thread threadWords;

        /**
         * Constructor for the class.
         */
        CalcWords() {

            threadWords = new Thread(this);

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

            try (Scanner scanner = new Scanner(path)) {

                String string;
                String[] wordsRead;


                while (!stop) {


                    while (scanner.hasNextLine()) {

                        string = scanner.nextLine();
                        wordsRead = string.split("[\\s]+");
                        result[0] = result[0] + wordsRead.length;

                    }

                }

                if (stop) {

                    this.threadWords.interrupt();
                    System.out.println("threadWords interrupted");

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

    /**
     * Class that informs about start of the application.
     */
    private class StartNotification implements Runnable {

        /**
         * Variable that refers to an instance of the Thread.
         */
        private Thread threadStart;

        /**
         * Constructor for the class.
         */
        StartNotification() {

            threadStart = new Thread(this);

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

            System.out.println("The program has started processing the file....");

        }
    }

    /**
     * Class that informs about finish of the application.
     */
    private class FinishNotification implements Runnable {

        /**
         * Variable that refers to an instance of the Thread.
         */
        private Thread threadFinish;

        /**
         * Constructor for the class.
         */
        FinishNotification() {

            threadFinish = new Thread(this);

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

            System.out.println("The program has finished calculations!");

        }
    }

    /**
     * Class that implements main logic of the application.
     * @throws Exception Exception
     */
    public void startApp() throws Exception {


        StartNotification startNotification = new StartNotification();
        CalcWords calcWords = new CalcWords();
        CalcSpaces calcSpaces = new CalcSpaces();
        FinishNotification finishNotification = new FinishNotification();
        Counter counter = new Counter();
        counter.threadCounter.start();

        startNotification.threadStart.start();
        startNotification.threadStart.join();

        calcSpaces.threadSpaces.start();
        calcSpaces.threadSpaces.join();

        calcWords.threadWords.start();
        calcWords.threadWords.join();

        finishNotification.threadFinish.start();
        finishNotification.threadFinish.join();

    }

    /**
     * Getter for the member result.
     * @return int[]
     */
    public int[] getResult() {
        return this.result;
    }
}
