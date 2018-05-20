package com.vgoryashko.consolechat;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class that splits a file to words.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/11/17
 */
public class SplitFileToArray {

    /**
     * Method that splits an input text file to words and creates an array of Strings.
     *
     * @param file                              a file to be split into words
     * @return String[]
     */
    public String[] splitFile(File file) {
        String[] result = null;
        String temp = null;
        int responcesCounter = 0;

        try (RandomAccessFile answersFile = new RandomAccessFile(file, "rw")) {
            answersFile.seek(0);
            do {
                temp = answersFile.readLine();
                if (temp != null) {
                    responcesCounter++;
                }
            } while (temp != null);
            answersFile.seek(0);
            result = new String[responcesCounter];
            for (int index = 0; index < responcesCounter; index++) {
                result[index] = answersFile.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
