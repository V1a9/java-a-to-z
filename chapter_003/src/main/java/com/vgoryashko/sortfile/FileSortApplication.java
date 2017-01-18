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
    private  FileWriter destFile;
    private int pieceOfData;
    private long fileIndex;
    private char[] readFirstString;
    private char[] readSecondString;
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
                destFile = new FileWriter(dest);
                fileIndex = sourceFile.getFilePointer();
                int counter = 0;
                do {
                    pieceOfData = (char) sourceFile.read();
                    counter++;
                } while ((pieceOfData != Character.LINE_SEPARATOR));

                firstStringLength = counter;
                readFirstString = new char[firstStringLength];
                sourceFile.seek(fileIndex);

                for (int index = 0; index < firstStringLength; index++) {
                    readFirstString[index] = (char) sourceFile.read();
                }

                destFile.write(readFirstString);

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
