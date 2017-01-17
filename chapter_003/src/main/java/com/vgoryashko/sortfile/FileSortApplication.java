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
    private FileWriter destFile;
    private byte pieceOfData;
    private long fileIndex;
    private byte[] readFirstString;
    private byte[] readSecondString;
    private int firstStringIndex;
    private int secondStringIndex;

    public void sort(File source, File dest) throws IOException {

        if(!source.exists()) {
            throw new FileNotFoundException("There is no such resource file.");
        } else {
            try {
                sourceFile = new RandomAccessFile(source, "r");
                destFile = new FileWriter(dest, true);
                fileIndex = sourceFile.getFilePointer();
                do {
                    firstStringIndex = 0;
                    secondStringIndex = 0;
                    do {
                        pieceOfData = (byte) sourceFile.read();
                        readFirstString[firstStringIndex++] = pieceOfData;
                    } while (pieceOfData != '\n');

                    }
                } while (pieceOfData == -1);

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
