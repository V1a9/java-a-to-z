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
    private  RandomAccessFile destFile;
    private RandomAccessFile tempFile1;
    private RandomAccessFile tempFile2;
    File temp1 = new File(String.format(".%stemp1.tmp", File.separator));
    File temp2 = new File(String.format(".%stemp2.tmp", File.separator));
    private int pieceOfData;
    private long sourceLength = 0;
    private long sourceStartOfFilePointer;
    private long sourceCurrentStringPointer;
    private long sourceNextStringPointer;
    private long destStartOfFilePointer;
    private long destCurrentStringPointer;
    private long destNextStringPointer;
    private long tempStartOfFilePointer;
    private long tempCurrentStringPointer;
    private long tempNextStringPointer;
    private int[] readFirstString;
    private int[] readSecondString;
    private int[] tempString;
    private int firstStringIndex;
    private int secondStringIndex;
    private int firstStringLength;
    private int secondStringLength;
    private boolean bigger = false;
    private boolean tempStringCreated = false;

    public void sortTempFiles(RandomAccessFile aTempFile1, RandomAccessFile aTempFile2) {

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
                firstStringIndex = 0;

                /**
                 * Main cycle that sorts files.
                 * Read strings and write them into two temp files.
                 */
                do {
                    if (tempStringCreated != true) {
                        pieceOfData = sourceFile.read();

                        do {
                            readFirstString[firstStringIndex] = pieceOfData;
                        } while (pieceOfData != 0x0D);

                        if (readFirstString.toString().getBytes().length + tempFile1.length() < 102400) {
                            for (int i = 0; i < readFirstString.length; i++) {
                                tempFile1.write(readFirstString[i]);
                            }
                        } else if (readFirstString.toString().getBytes().length + tempFile2.length() < 102400) {
                            for (int i = 0; i < readFirstString.length; i++) {
                                tempFile2.write(readFirstString[i]);
                            }
                        } else {
                            tempString = Arrays.copyOf(readFirstString, readFirstString.length);
                            tempStringCreated = true;

                            /**
                             * Temp array of bytes that can't be filled into both temp files has been created.
                             * Temp files have to be sorted out and merged into the dest file.
                             */
                        sortTempFiles(tempFile1, tempFile2);

                        }
                    } else {
                        for (int i = 0; i < tempString.length; i++) {
                            tempFile1.write(tempString[i]);
                        }
                        tempStringCreated = false;
                    }

                } while (pieceOfData != -1);

                do {

                    if (sourceFile.length() - sourceFile.getFilePointer() > 102400) {
                        try {
                            tempFile1 = new RandomAccessFile(temp1, "rw");
                            tempFile1.setLength(102400);
                            tempFile2 = new RandomAccessFile(temp2, "rw");
                            tempFile2.setLength(102400);

                            firstStringIndex = 0;
                            do {
                                pieceOfData = sourceFile.read();
                                readFirstString[firstStringIndex++] = pieceOfData;
                            } while(pieceOfData != 0x0D);

                            if (tempFile1.length() + readFirstString.length < 102400) {
                                for (int i = 0; i < readFirstString.length; i++) {
                                    tempFile1.write(readFirstString[i]);
                                }
                            } else if (tempFile2.length() + readFirstString.length < 102400) {
                                for (int i = 0; i < readFirstString.length; i++) {
                                    tempFile2.write(readFirstString[i]);
                                }
                            } else {
                                tempString = Arrays.copyOf(readFirstString, readFirstString.length);
                                tempStringCreated = true;
                            }

                        } catch (IOException ie) {
                            System.out.println("IOException.");
                        }
                    }
                    sourceLength++;
                } while (sourceLength != sourceFile.length());

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
