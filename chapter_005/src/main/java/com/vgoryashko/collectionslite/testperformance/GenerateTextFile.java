package com.vgoryashko.collectionslite.testperformance;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

/**
 * Class that creates test text file that contains 256 randomly generated strings.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/21/17
 */
public class GenerateTextFile {

    /**
     * Constant that stores a file separator value.
     */
    private static final String FS = File.separator;

    /**
     * Constant that stores a path to an auxiliary directory.
     */
    private static final Path AUXDIR = Paths.get(String.format(".%sauxiliary", FS));

    /**
     * Constant that stores a path to a TESTFILE.txt file.
     */
    private static final Path TESTFILE = Paths.get(String.format(".%sauxiliary%stestFile.txt", FS, FS));

    /**
     * Method that checks if an auxiliary directory and testFile.txt are available.
     *
     * @return <code>boolean</code>
     */
    public boolean check() {

        boolean exist = false;

        try {

            if (!Files.exists(AUXDIR)) {
                Files.createDirectory(AUXDIR);
                Files.createFile(TESTFILE);
                exist = false;
            } else {
                if (Files.exists(TESTFILE)) {
                    exist = true;
                } else {
                    Files.createFile(TESTFILE);
                    exist = false;
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return exist;
    }

    /**
     * Method that generates a content for the TESTFILE.txt.
     *
     * @return <code>boolean</code>
     */
    public boolean generate() {

        boolean result = false;
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_,.! -''";

        Random randomLen = new Random(100000);

        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(TESTFILE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING))) {

            int stringNumber = 100000;
            int stringLen;
            String tmp = null;

            for (int i = 0; i < stringNumber; i++) {

                stringLen = randomLen.nextInt(128);
                StringBuilder sb = new StringBuilder(stringLen);

                for (int j = 0; j < stringLen; j++) {
                    tmp = sb.append(chars.charAt(randomLen.nextInt(chars.length()))).toString();
                }

                out.println(tmp);
            }

            result = true;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }
}
