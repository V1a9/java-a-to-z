package com.vgoryashko.sortfile;

import java.io.*;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 17.01.2017
 */
public class FileSortApplication {

    private RandomAccessFile sourceFile;
    private  RandomAccessFile destFile;
    private File temp;
    private RandomAccessFile tempFile;
    private int pieceOfData;
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
    private int firstStringIndex;
    private int secondStringIndex;
    private int firstStringLength;
    private int secondStringLength;
    private boolean bigger = false;

    public void sort(File source, File dest) throws IOException {

        if(!source.exists()) {
            throw new FileNotFoundException("There is no such resource file.");
        } else {
            try {
                if(dest.exists()) {
                    dest.delete();
                } else if (temp.exists()) {
                    temp.delete();
                }
                sourceFile = new RandomAccessFile(source, "r");
                destFile = new RandomAccessFile(dest, "rw");
                temp  = new File(String.format(".%stemp.txt", File.separator));
                tempFile = new RandomAccessFile(temp, "rw");
                sourceStartOfFilePointer = sourceFile.getFilePointer();
                destStartOfFilePointer = destFile.getFilePointer();
                tempStartOfFilePointer = tempFile.getFilePointer();
                int firstStringCounter = 0;
                int secondStringCounter = 0;
                int numberOfStringsInSource = 0;

                do {
                    pieceOfData = sourceFile.read();
                    if(pieceOfData != -1)
                        destFile.write(pieceOfData);
                } while (pieceOfData != -1);

                sourceFile.seek(sourceStartOfFilePointer);

                /**
                 * Counts number of strings in a source file.
                 */
                do {
                    pieceOfData = sourceFile.read();
                    if (pieceOfData == 0x0D) {
                        numberOfStringsInSource++;
                    }
                } while (pieceOfData != -1);

                sourceCurrentStringPointer = sourceStartOfFilePointer;
                destCurrentStringPointer = destStartOfFilePointer;
                tempCurrentStringPointer = tempStartOfFilePointer;

                /**
                 *
                 */
                for (int i = 0; i < numberOfStringsInSource; i++) {
                    for (int j = 0; j < numberOfStringsInSource; j++ ) {

                        if (!bigger) {
                            sourceFile.seek(sourceCurrentStringPointer);
                            firstStringCounter = 0;
                            do {
                                pieceOfData = sourceFile.read();
                                firstStringCounter++;
                            } while (pieceOfData != 0x0D);

                            firstStringLength = firstStringCounter;
                            readFirstString = new int[firstStringLength];

                            sourceNextStringPointer = sourceFile.getFilePointer();
                            sourceFile.seek(sourceCurrentStringPointer);

                            for (int index = 0; index < firstStringLength; index++) {
                                readFirstString[index] = sourceFile.read();
                            }
                            secondStringCounter = 0;
                            do {
                                pieceOfData = sourceFile.read();
                                secondStringCounter++;
                            } while (pieceOfData != 0x0D);

                            secondStringLength = secondStringCounter;
                            readSecondString = new int[secondStringLength];
                            sourceFile.seek(sourceNextStringPointer);

                            for (int index = 0; index < secondStringLength; index++) {
                                readSecondString[index] = sourceFile.read();
                            }

                        } else {
                            sourceFile.seek(sourceNextStringPointer);
                            secondStringCounter = 0;
                            do {
                                pieceOfData = sourceFile.read();
                                secondStringCounter++;
                            } while (pieceOfData != 0x0D);

                            secondStringLength = secondStringCounter;
                            readSecondString = new int[secondStringLength];
                            sourceFile.seek(sourceNextStringPointer);

                            for (int index = 0; index < secondStringLength; index++) {
                                readSecondString[index] = sourceFile.read();
                            }
                        }

                        if (readFirstString.length > readSecondString.length) {
                            tempFile.seek(tempCurrentStringPointer);
                            for(int element : readFirstString) {
                                tempFile.write(element);
                            }
                            tempNextStringPointer = tempFile.getFilePointer();
                            for(int element : readSecondString) {
                                tempFile.write(element);
                            }
                            tempCurrentStringPointer = tempNextStringPointer;
                            sourceCurrentStringPointer = sourceNextStringPointer;
                            bigger = true;
                        } else {
                            bigger = false;
                        }
                    }
                }

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
