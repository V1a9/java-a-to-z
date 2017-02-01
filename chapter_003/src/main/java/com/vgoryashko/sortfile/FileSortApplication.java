package com.vgoryashko.sortfile;

import java.io.*;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.10
 * @since 01.02.2017
 */
public class FileSortApplication {

    private RandomAccessFile sourceFile;
    private RandomAccessFile destFile;
    private final String dirSeparator = System.getProperty("file.separator");
    private File tempDir = new File(String.format(".%stmp", dirSeparator));
    private File temp;
    private String readLine;
    private long sourceCurrentStringPointer;

    /**
     * Method that reads strings from a file.
     */
    public String readString(RandomAccessFile aFile) throws IOException {
        String result;
        readLine = aFile.readLine();
        if (readLine == null) {
            result = null;
        } else {
            result = readLine.concat(System.getProperty("line.separator"));
        }
        return result;
    }

    /**
     * Method that split source.txt on several *.tmp files.
     */
    public void splitSource() throws IOException {
        boolean fileIsFull = false;
        long tempFilesCounter = 0;
        boolean terminateIteration = false;
        String buffer;

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
//                        terminateIteration = true;
                        break;
                    } else if (tempFile.length() + buffer.length() < 102400) {
                        tempFile.write(buffer.getBytes());
                    } else {
                        terminateIteration = true;
                        sourceFile.seek(sourceCurrentStringPointer);
                    }
                } while (!terminateIteration);
            } catch (IOException ioe) {
                throw new IOException("IOException in splitSource method.");
            }
            terminateIteration = false;
        }while (buffer != null) ;
    }

    /**
     * Method that defines a shorter line in a file.
     */
    public String defineShorterLine(RandomAccessFile file, long fileCurrentLinePointer, long fileNextLinetPointer) throws IOException {
        String result = null;

        String minFromfile = null;
        fileCurrentLinePointer = file.getFilePointer();
        do {
            file.seek(fileCurrentLinePointer);
            do {
                readLine = file.readLine();
                fileNextLinetPointer = file.getFilePointer();
                if (minFromfile == null) {
                    minFromfile = readLine;
                } else {
                    if (readLine.length() < minFromfile.length()) {
                        minFromfile = readLine;
                    }
                }
            } while (readLine != null);
            fileCurrentLinePointer = fileNextLinetPointer;
        } while (fileCurrentLinePointer != file.length());
        return result;
    }

    /**
     * Method that sorts two temp files.
     */
    public void sortTempFiles() throws IOException {
        File[] listOfFiles;
        long tempFilesCounter = 0;
        String minFromTemp1 = null;
        String minFromTemp2 = null;
        long temp1CurrentLinePointer;
        long temp1NextLinetPointer;
        long temp2CurrentLinePointer;
        long temp2NextLinetPointer;
        do {
            listOfFiles = tempDir.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                do {
                    try {
                        RandomAccessFile temp1 = new RandomAccessFile(listOfFiles[i], "rw");
                        RandomAccessFile temp2 = new RandomAccessFile(listOfFiles[++i], "rw");
                        RandomAccessFile temp3 = new RandomAccessFile(new File(String.format(".%stmp%stemp%d.txt", 
                                                                                        dirSeparator, dirSeparator, 
                                                                                        tempFilesCounter++)), "rw");
                        

                        if (minFromTemp1.length() < minFromTemp2.length()) {
                            temp3.write(minFromTemp1.getBytes());
                            temp3.write(minFromTemp2.getBytes());
                        } else {
                            temp3.write(minFromTemp2.getBytes());
                            temp3.write(minFromTemp1.getBytes());
                        }                         
                        
                    } catch (IOException ioe) {
                        throw new IOException("IOException in sortTempFiles method.");
                    }
                } while (true);
            }
        } while (listOfFiles.length != 1);
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
                System.out.println("IOException in sort method.");
            }
        }
    }
}
