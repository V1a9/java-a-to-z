package com.vgoryashko.sortfile;

import java.io.*;
import java.util.Arrays;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 30.01.2017
 */
public class FileSortApplication {

    private RandomAccessFile sourceFile;
    private RandomAccessFile destFile;
    private RandomAccessFile tempFile1;
    private RandomAccessFile tempFile2;
    private RandomAccessFile tempFile3;
    private File temp1 = new File(String.format(".%stemp1.txt", File.separator));
    private File temp2 = new File(String.format(".%stemp2.txt", File.separator));
    private File temp3 = new File(String.format(".%stemp3.txt", File.separator));
    private int pieceOfData;
    private long sourceLength = 0;
    private long sourceStartOfFilePointer;
    private long sourceCurrentStringPointer;
    private long sourceNextStringPointer;
    private int[] readFirstString;
    private int[] tempString;
    private int firstStringIndex;
    private int secondStringIndex;
    private int firstStringLength;
    private int secondStringLength;
    private boolean bigger = false;
    boolean tempStringCreated = false;

    /**
     * Method that creates two temp files.
     */
    public void createTwoTempFiles() throws IOException {

        if (temp1.exists() & temp2.exists()) {
            temp1.delete();
            temp2.delete();
        }

        try {

            tempFile1 = new RandomAccessFile(temp1, "rw");
            tempFile2 = new RandomAccessFile(temp2, "rw");
            sourceCurrentStringPointer = sourceFile.getFilePointer();

            boolean stringComplete = false;

                do {
                    if (tempStringCreated) {
                        for (int element : tempString) {
                            tempFile1.write(element);
                        }
                        tempStringCreated = false;
                        tempString = null;
                    } else {
                        pieceOfData = sourceFile.read();

                        if (pieceOfData != 0x0A) {
                            firstStringLength++;
                        } else if (pieceOfData == 0x0A) {
                            firstStringLength++;
                            stringComplete = true;
                        }
                        if (pieceOfData == -1) {
                            firstStringLength++;
                            stringComplete = true;
                        }

                        if (stringComplete) {

                            readFirstString = new int[firstStringLength];
                            sourceFile.seek(sourceCurrentStringPointer);

                            firstStringIndex = 0;

                            do {
                                readFirstString[firstStringIndex++] = sourceFile.read();
                            } while (firstStringIndex < firstStringLength);

                            sourceNextStringPointer = sourceFile.getFilePointer();

                            stringComplete = false;

                            if (tempFile1.length() + readFirstString.length < 256) {
                                for (int element : readFirstString) {
                                    tempFile1.write(element);
                                }
                            } else if (tempFile2.length() + readFirstString.length < 256) {
                                for (int element : readFirstString) {
                                    tempFile2.write(element);
                                }
                            } else {
                                tempString = Arrays.copyOf(readFirstString, readFirstString.length);
                                tempStringCreated = true;
                            }

                            firstStringLength = 0;
                        }

                        sourceCurrentStringPointer = sourceNextStringPointer;
                    }

                } while (tempStringCreated != true || pieceOfData == -1);

        } catch (IOException ioe) {
            throw new IOException("IOException while creating temp files.");
        }
    }

    /**
     * Method that creates last temp file.
     */
    public void createLastTemp() throws IOException {

        if (temp3.exists()) {
            temp3.delete();
        }

        try {

            sourceFile.seek(sourceCurrentStringPointer);

            tempFile3 = new RandomAccessFile(temp3, "rw");

            boolean stringComplete = false;

            do {
                if (tempStringCreated) {
                    for (int element : tempString) {
                        tempFile3.write(element);
                    }
                    tempStringCreated = false;
                    tempString = null;
                } else {
                    pieceOfData = sourceFile.read();

                    if (pieceOfData != 0x0A) {
                        firstStringLength++;
                    } else if (pieceOfData == 0x0A) {
                        stringComplete = true;
                    }
                    if (pieceOfData == -1) {
                        firstStringLength--;
                        stringComplete = true;
                    }

                    if (stringComplete) {

                        readFirstString = new int[firstStringLength];
                        sourceFile.seek(sourceCurrentStringPointer);

                        firstStringIndex = 0;

                        do {
                            readFirstString[firstStringIndex++] = sourceFile.read();
                        } while (firstStringIndex < firstStringLength);

                        sourceNextStringPointer = sourceFile.getFilePointer();

                        stringComplete = false;

                        for (int element : readFirstString) {
                            tempFile3.write(element);
                        }

                        firstStringLength = 0;
                    }

                    sourceCurrentStringPointer = sourceNextStringPointer;
                }

            } while (pieceOfData != -1);

        } catch (IOException ioe) {
            throw new IOException("IOException when creating last temp file.");
        }
    }

    /**
     * Method that sorts two temp files.
     * @param aTempFile1
     * @param aTempFile2
     */
    public void sortTempFiles(RandomAccessFile aTempFile1, RandomAccessFile aTempFile2) {

    }

    /**
     * Method that sorts last temp file.
     */
    public void sortLastFiles(RandomAccessFile aTempFile1) {

    }

    public void sort(File source, File dest) throws IOException {

        if (!source.exists()) {
            throw new FileNotFoundException("There is no such resource file.");
        } else {
            try {
                if (dest.exists()) {
                    dest.delete();
                }

                sourceFile = new RandomAccessFile(source, "r");
                destFile = new RandomAccessFile(dest, "rw");

                /**
                 * Check size of remaining part of the source file.
                 */
                do {

                    if (sourceFile.getFilePointer() != sourceFile.length()) {

                        if (sourceFile.length() - sourceFile.getFilePointer() > 256) {
                            this.createTwoTempFiles();
                            this.sortTempFiles(this.tempFile1, this.tempFile2);
                        } else {
                            this.createLastTemp();
                            this.sortLastFiles(this.tempFile1);
                        }
                    }
                } while (sourceFile.getFilePointer() != sourceFile.length());

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
