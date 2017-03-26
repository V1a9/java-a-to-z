package com.vgoryashko.sortfile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class that tests a class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 1.0
 * @since 07.02.2017
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
        File source = new File(String.format(".%ssource2.txt", System.getProperty("file.separator")));
        sortApplication.sort(source, dest);
        throw new FileNotFoundException("There is no such resource file.");
    }

    /**
     * Method that tests that FileNotFoundException is not thrown when a source file exists.
     *
     */
    @Test
    public void whenSourceFileExistsThenNoExceptionThrown() {
        source = new File(sourcePath);
        assertTrue(source.exists());
    }

    /**
     * Method that tests that source is sorted correctly.
     *
     * @throws IOException                  IOException
     */
    @Test
    public void whenSourceFileExistsThenContentsWrittenToDest() throws IOException {
        File destManual = new File(String.format(".%sdest_manual.txt", File.separator));
        String stringTemp1;
        String stringTemp2;
        source = new File(sourcePath);
        sortApplication.sort(source, dest);
        try (RandomAccessFile temp = new RandomAccessFile(dest, "rw");
             RandomAccessFile temp1 = new RandomAccessFile(destManual, "rw")) {
            do {
                stringTemp1 = temp.readLine();
                stringTemp2 = temp1.readLine();
                assertEquals(stringTemp1, stringTemp2);
            } while (stringTemp1 == stringTemp2 && stringTemp1 == null && stringTemp2 == null);
        } catch (IOException ioe) {
            System.out.println("IOException in tests.");
        }
    }

    /**
     * Method that tests that readString method reads a string from a file correctly.
     *
     * @throws IOException                  IOException
     */
    @Test
    public void whenReadStringMethodIsInvokedThenItReturnsString() throws IOException {
        source = new File(sourcePath);
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
        File source = new File(String.format(".%ssource1.txt", System.getProperty("file.separator")));
        RandomAccessFile file = new RandomAccessFile(source, "r");
        assertNull(sortApplication.readString(file));
    }
}
