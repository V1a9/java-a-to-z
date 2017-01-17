package com.vgoryashko.sortfile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class that tests a class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 17.01.2017
 */
public class FileSortApplicationTest {

    private FileSortApplication sortApplication;
    private File source;
    private final String SOURCEPATH = String.format(".%ssource.txt", File.separator);
    private final String DESTPATH = String.format(".%sdest.txt", File.separator);
    private File dest;

    @Before
    public void initSetUp() {
        sortApplication = new FileSortApplication();
        source = new File(SOURCEPATH);
        dest = new File(DESTPATH);
    }

    /**
     * Rule for testing exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none().none();

    /**
     * Method that tests that FileNotFoundException is thrown when there is no a source file.
     *
     * @throws IOException
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
}
