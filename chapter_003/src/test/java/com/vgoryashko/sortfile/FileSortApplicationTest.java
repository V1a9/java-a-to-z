package com.vgoryashko.sortfile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class that tests a class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 06.02.2017
 */
public class FileSortApplicationTest {

    /**
     * Variable that is used for referencing to an object of FileSortApplication class.
     */
    private FileSortApplication sortApplication;

    /**
     * Variable that is used for referencing to an object of File class.
     */
    private File source;

    /**
     * Variable that is used for referencing to an object of File class.
     */
    private File dest;

    /**
     * Variable that stores a path of source file.
     */
    private final String sourcePath = String.format(".%ssource.txt", File.separator);
    /**
     * Variable that stores a path of dest file.
     */
    private final String destPath = String.format(".%sdest.txt", File.separator);

    /**
     * Method that initialise test environments.
     */
    @Before
    public void initSetUp() {
        sortApplication = new FileSortApplication();
        source = new File(sourcePath);
        dest = new File(destPath);
    }

    /**
     * Rule for testing exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none().none();

    /**
     * Method that tests that FileNotFoundException is thrown when there is no a source file.
     *
     * @throws IOException                  IOException
     */
    @Test
    public void whenThereIsNoFileThenExceptionThrown() throws IOException {
        expectedException.expectMessage("There is no such resource file.");
        sortApplication.sort(source, dest);
        throw new FileNotFoundException("There is no such resource file.");
    }

    /**
     * Method that tests that FileNotFoundException is not thrown when a source file exists.
     *
     */
    @Test
    public void whenSourceFileExistsThenNoExceptionThrown() {
        assertTrue(source.exists());
    }

    /**
     * Method that tests that source is sorted correctly.
     *
     * @throws IOException                  IOException
     */
    @Test
    public void whenSourceFileExistsThenContentsWrittenToDest() throws IOException {
        sortApplication.sort(source, dest);
    }

    /**
     * Method that tests that readString method reads a string from a file correctly.
     *
     * @throws IOException                  IOException
     */
    @Test
    public void whenReadStringMethodIsInvokedThenItReturnsString() throws IOException {
        RandomAccessFile file = new RandomAccessFile(source, "r");
        assertThat(sortApplication.readString(file), is(String.format("j8u45%s", System.getProperty("line.separator"))));
    }

    /**
     * Method that tests throwing of IOException if a source file is empty.
     *
     * @throws IOException                  IOException
     */
    @Test
    public void whenReadMethodIsInvokedWithNullThenItsReturned() throws IOException {
        RandomAccessFile file = new RandomAccessFile(source, "r");
        assertNull(sortApplication.readString(file));
    }
}
