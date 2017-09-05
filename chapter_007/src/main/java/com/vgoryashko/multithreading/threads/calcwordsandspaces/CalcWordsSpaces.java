package com.vgoryashko.multithreading.threads.calcwordsandspaces;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Class that implements calculation of words and spaces in a given file concurrently.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/05/17
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
     * Constructor for the class.
     * @param path to a file to be processed.
     */
    public CalcWordsSpaces(Path path) {

        this.path = path;
        this.result  = new int[2];

    }

    /**
     * Class that calculates spaces in a given file.
     */
    private class CalcSpaces implements Runnable {

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
                while ((charRead = reader.read()) != -1) {

                    if (0x0020 == charRead) {

                        result[1] = ++result[1];

                    }

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

                while (scanner.hasNextLine()) {

                    string = scanner.nextLine();
                    wordsRead = string.split("[\\s]+");
                    result[0] = result[0] + wordsRead.length;

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

    /**
     * Class that implements main logic of the application.
     * @throws Exception Exception
     */
    public void startApp() throws Exception {

        new Thread(new CalcSpaces()).start();
        new Thread(new CalcWords()).start();
        Thread.sleep(20);

    }

    /**
     * Getter for the member result.
     * @return int[]
     */
    public int[] getResult() {
        return this.result;
    }
}
