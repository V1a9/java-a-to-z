package com.vgoryashko.sortfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;


/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 1.0
 * @since 07.02.2017
 */
public class FileSortApplication {
    /**
     * Variable for creation of an object for source.txt.
     */
    private RandomAccessFile sourceFile;
    /**
     * Variable that defines size for temp files.
     */
    private int fileSize = 102400;
    /**
     * Variable that stores directory separator used in OS.
     */
    private final String dirSeparator = System.getProperty("file.separator");
    /**
     * Variable that stores path for dest.txt.
     */
    private File dest = new File(String.format(".%sauxiliary%sdest.txt", dirSeparator, dirSeparator));
    /**
     * * Variable that stores path for tmp directory.
     */
    private File tempDir = new File(String.format(".%sauxiliary%stmp", dirSeparator, dirSeparator));
    /**
     * Variable that used for counting of temp files.
     */
    private long tempFilesCounter = 0;
    /**
     * Variable that used for storing of strings read from input stream.
     */
    private String string;

    /**
     * Method that reads strings from a file.
     *
     * @param aFile                         file a string will be read from
     * @return String                       string read from a file.
     */
    public String readString(RandomAccessFile aFile) {
        String result = null;
        try {
            string = aFile.readLine();
            if (string == null) {
                result = null;
            } else {
                result = string.concat(System.getProperty("line.separator"));
            }
        } catch (IOException ioe) {
            System.out.println("IOException in readString method.");
        }
        return result;
    }

    /**
     * Method that split source.txt on several *.tmp files with value that set in fileSize.
     */
    public void splitSource() {
        boolean terminateIteration = false;
        long sourceCurrentStringPointer;
        File temp;
        do {
            temp = new File(String.format(".%sauxiliary%stmp%stemp%d.txt", dirSeparator, dirSeparator, dirSeparator, tempFilesCounter++));
            try (RandomAccessFile tempFile = new RandomAccessFile(temp, "rw")) {

                do {
                    sourceCurrentStringPointer = sourceFile.getFilePointer();
                    string = this.readString(sourceFile);
                    if (string == null) {
                        terminateIteration = true;
                    } else if (tempFile.length() + string.length() < fileSize) {
                        tempFile.write(string.getBytes());
                    } else {
                        terminateIteration = true;
                        sourceFile.seek(sourceCurrentStringPointer);
                    }
                } while (!terminateIteration);
            } catch (IOException ioe) {
                System.out.println("IOException in splitSource method.");
            }
            terminateIteration = false;
        } while (string != null);
    }

    /**
     * Method that defines a shorter line in a file.
     *
     */
    public void sortTempFiles() {
        Comparator<String> stringsLengthSort = new StringLengthSort();
        File[] listOfFiles = tempDir.listFiles();
        int numberOfTempFiles = listOfFiles.length;

        for (int i = 0; i < numberOfTempFiles; i++) {
                try (RandomAccessFile temp1 = new RandomAccessFile(listOfFiles[i], "rw");
                     RandomAccessFile temp2 = new RandomAccessFile(new File(String.format(
                             ".%sauxiliary%stmp%stemp%d.txt", dirSeparator, dirSeparator, dirSeparator, tempFilesCounter++)), "rw");) {

                    temp1.seek(0);
                    int stringsCounter = 0;
                    do {
                        string = temp1.readLine();
                        if (string != null) {
                            stringsCounter++;
                        }
                    } while (string != null);
                    String[] stringsFromTemp = new String[stringsCounter];
                    temp1.seek(0);

                    for (int j = 0; j < stringsCounter; j++) {
                        stringsFromTemp[j] = temp1.readLine().concat(System.getProperty("line.separator"));
                    }

                    Arrays.sort(stringsFromTemp, stringsLengthSort);
                    for (String string : stringsFromTemp) {
                        temp2.write(string.getBytes());
                    }

                } catch (IOException ieo) {
                    System.out.println("IOException in sortTempFiles method.");
                }

            try {
                Files.delete(listOfFiles[i].toPath());
            } catch (IOException ioe) {
                // File permission problems are caught here.
                System.err.println(ioe);
            }

            listOfFiles[i].delete();

        }
    }

    /**
     * Method that sorts two temp files.
     *
     */
    public void mergeTempFiles() {
        File[] listOfFiles = tempDir.listFiles();
        int numberOfTempFiles = 0;
        String minFromTemp1 = null;
        String minFromTemp2 = null;
        boolean firstIteration = true;
        boolean minFromTemp1Bigger = true;

            do {
                try (RandomAccessFile temp1 = new RandomAccessFile(listOfFiles[numberOfTempFiles], "rw");
                     RandomAccessFile temp2 = new RandomAccessFile(listOfFiles[++numberOfTempFiles], "rw");
                     RandomAccessFile temp3 = new RandomAccessFile(new File(String.format(
                             ".%sauxiliary%stmp%stemp%d.txt", dirSeparator, dirSeparator, dirSeparator, tempFilesCounter++)), "rw");) {

                    do {
                        if (firstIteration) {
                            minFromTemp1 = temp1.readLine();
                            if (minFromTemp1 != null) {
                                minFromTemp1 = minFromTemp1.concat(System.getProperty("line.separator"));
                            }
                            minFromTemp2 = temp2.readLine();
                            if (minFromTemp2 != null) {
                                minFromTemp2 = minFromTemp2.concat(System.getProperty("line.separator"));
                            }
                            firstIteration = false;

                        } else if (minFromTemp1Bigger) {
                            minFromTemp2 = temp2.readLine();
                            if (minFromTemp2 != null) {
                                minFromTemp2 = minFromTemp2.concat(System.getProperty("line.separator"));
                            }
                        } else {
                            minFromTemp1 = temp1.readLine();
                            if (minFromTemp1 != null) {
                                minFromTemp1 = minFromTemp1.concat(System.getProperty("line.separator"));
                            }
                        }

                        if (minFromTemp1 != null) {
                            if (minFromTemp2 != null) {
                                if (minFromTemp1.length() < minFromTemp2.length() && minFromTemp1.length() != minFromTemp2.length()) {
                                    temp3.write(minFromTemp1.getBytes());
                                    minFromTemp1Bigger = false;
                                } else if (minFromTemp1.length() == minFromTemp2.length()) {
                                    temp3.write(minFromTemp1.getBytes());
                                    temp3.write(minFromTemp2.getBytes());
                                    firstIteration = true;
                                } else if (minFromTemp2.length() < minFromTemp1.length() && minFromTemp2.length() != minFromTemp1.length()) {
                                    temp3.write(minFromTemp2.getBytes());
                                    minFromTemp1Bigger = true;
                                } else if (minFromTemp1.length() == minFromTemp2.length()) {
                                    temp3.write(minFromTemp1.getBytes());
                                    temp3.write(minFromTemp2.getBytes());
                                    firstIteration = true;
                                }
                            }
                        }
                        if (minFromTemp1 == null && minFromTemp2 != null) {
                            temp3.write(minFromTemp2.getBytes());
                            minFromTemp1Bigger = true;
                        } else if (minFromTemp2 == null && minFromTemp1 != null) {
                            temp3.write(minFromTemp1.getBytes());
                            minFromTemp1Bigger = false;
                        }

                    } while (minFromTemp1 != null | minFromTemp2 != null);

                } catch (IOException ioe) {
                    System.out.println("IOException in sortTempFiles method.");
                }
                listOfFiles[numberOfTempFiles--].delete();
                listOfFiles[numberOfTempFiles++].delete();
                listOfFiles = tempDir.listFiles();
                firstIteration = true;
                if (listOfFiles.length > 1) {
                    numberOfTempFiles--;
                }
            } while (listOfFiles.length != 1);
        listOfFiles = tempDir.listFiles();
        listOfFiles[0].renameTo(dest);
        tempDir.delete();
    }

    /**
     * Method that sort strings in a source.txt file and saves a result into a dest.txt file.
     *
     * @param source                        source file
     * @param dest                          destination file where result is going to be written
     * @throws FileNotFoundException        FileNotFoundException
     */
    public void sort(File source, File dest) throws FileNotFoundException {

        if (!source.exists()) {
            throw new FileNotFoundException("There is no such resource file.");
        } else {
            try {
                if (tempDir.exists()) {
                    if (tempDir.listFiles().length != 0) {
                        for (File fileName : tempDir.listFiles()) {
                            fileName.getAbsolutePath();
                            fileName.delete();
                        }
                    }
                } else {
                    tempDir.mkdir();
                }
                if (dest.exists()) {
                    dest.delete();
                }
                sourceFile = new RandomAccessFile(source, "r");

                if (source.length() < 1048576) {
                    fileSize = 256;
                    this.splitSource();
                    this.sortTempFiles();
                    this.mergeTempFiles();
                } else {
                    this.splitSource();
                    this.sortTempFiles();
                    this.mergeTempFiles();
                }

            } catch (IOException ie) {
                System.out.println("IOException in sort method.");
            }
        }
    }
}
