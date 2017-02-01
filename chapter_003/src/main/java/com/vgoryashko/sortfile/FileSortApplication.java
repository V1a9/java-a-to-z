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
    private int tempFilesCounter = 0;
    private File temp;
    private String pieceOfData;
    private long sourceCurrentStringPointer;
    private long sourceNextStringPointer;

    private boolean bigger = false;

    /**
     * Method that reads strings from a file.
     */
    public String readString(RandomAccessFile aFile) throws IOException {
        String result;
        pieceOfData = aFile.readLine();
        if (pieceOfData == null) {
            result = null;
        } else {
            result = pieceOfData.concat(System.getProperty("line.separator"));
        }
        return result;
    }

    /**
     * Method that split source.txt on several *.tmp files.
     */
    public void splitSource() throws IOException {
        boolean fileIsFull = false;
        String buffer;
        String dirSeparator = System.getProperty("file.separator");
        File tempDir = new File(String.format(".%stmp", dirSeparator));

        if (tempDir.exists()) {
            tempDir.delete();
        }
        tempDir.mkdir();

        do {
            try {
                File temp = new File(String.format(".%stmp%stemp%d.txt", dirSeparator, dirSeparator, tempFilesCounter++));
                RandomAccessFile tempFile = new RandomAccessFile(temp, "rw");
                do {
                    sourceCurrentStringPointer = sourceFile.getFilePointer();
                    buffer = this.readString(sourceFile);
                    if (buffer == null) {
                        break;
                    } else if (tempFile.length() + buffer.length() < 1024100) {
                        tempFile.write(buffer.getBytes());
                    } else {
                        fileIsFull = true;
                        sourceFile.seek(sourceCurrentStringPointer);
                    }
                } while (buffer != null || !fileIsFull);
            } catch (IOException ioe) {
                throw new IOException("IOException in splitSource method.");
            }
            fileIsFull = false;
        }while (buffer != null) ;
    }

    /**
     * Method that sorts two temp files.
     * @param aTempFile1
     * @param aTempFile2
     */
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

                this.splitSource();

            } catch (IOException ie) {
                System.out.println("IOException.");
            }
        }
    }
}
