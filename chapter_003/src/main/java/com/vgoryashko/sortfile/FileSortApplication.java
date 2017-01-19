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
    private String pieceOfData;
    private long startOfFilePointer;
    private long currentStringPointer;
    private long nextStringPointer;
    private int[] readFirstString;
    private int[] readSecondString;
    private int firstStringIndex;
    private int secondStringIndex;
    private int firstStringLength;
    private int secondStringLength;

    public void sort(File source, File dest) throws IOException {

        if(!source.exists()) {
            throw new FileNotFoundException("There is no such resource file.");
        } else {
            try {
                sourceFile = new RandomAccessFile(source, "r");
                destFile = new RandomAccessFile(dest, "rw");
                temp  = new File(String.format(".%stemp.txt", File.separator));
                tempFile = new RandomAccessFile(temp, "rw");
                startOfFilePointer = sourceFile.getFilePointer();
                currentStringPointer = sourceFile.getFilePointer();
                nextStringPointer = sourceFile.getFilePointer();
                int firstStringCounter = 0;
                int secondStringCounter = 0;
                int numberOfStringsInSource = 1;

                do {
                    pieceOfData = sourceFile.readLine();
                    destFile.write(pieceOfData.getBytes());
                } while (pieceOfData != null);

//                /**
//                 * Counts number of strings in a source file.
//                 */
//                do {
//                    pieceOfData = sourceFile.readLine();
//                    if (pieceOfData == 13)
//                        numberOfStringsInSource++;
//                } while (pieceOfData != -1);
//
//                /**
//                 *
//                 */
//                for (int i = 0; i < numberOfStringsInSource; i++) {
//                    for (int j = 0; j < numberOfStringsInSource; j++ ) {
//                        sourceFile.seek(startOfFilePointer);
//                        do {
//                            pieceOfData = sourceFile.read();
//                            firstStringCounter++;
//                        } while (pieceOfData != 13);
//
//                        firstStringLength = firstStringCounter;
//                        readFirstString = new int[firstStringLength];
//                        sourceFile.seek(currentStringPointer);
//
//                        nextStringPointer = sourceFile.getFilePointer();
//
//                        for (int index = 0; index < firstStringLength; index++) {
//                            readFirstString[index] = sourceFile.read();
//                        }
//
//                        do {
//                            pieceOfData = sourceFile.read();
//                            secondStringCounter++;
//                        } while (pieceOfData != 13);
//
//                        secondStringLength = secondStringCounter;
//                        readSecondString = new int[secondStringLength];
//                        sourceFile.seek(nextStringPointer);
//
//                        for (int index = 0; index < secondStringLength; index++) {
//                            readSecondString[index] = sourceFile.read();
//                        }
//
//                        if (readFirstString.length > readSecondString.length) {
//
//                        }
//                    }
//                }

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
