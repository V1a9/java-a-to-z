package com.vgoryashko.search;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.*;

/**
 * Class that test files' names to a file.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 4/3/17
 */
public class SearchTest {

    private static final String NL = System.getProperty("line.separator");

    @Before
    public void setupEnvironment() {

    }

    /**
     * Test search.
     */
    @Test
    public void searchTest() {

        File aux = new File(String.format("..%sauxiliary%s.%s", File.separator, File.separator, File.separator));
        File auxCan = null;
        try {
            auxCan = aux.getCanonicalFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String[] args = new String[]{"-d", auxCan.toString(), "-n", "*.txt", "-m", "-o", "log.txt"};
        try {
            Search.main(args);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Test case for testing user input and its parsing by application.
     */
    @Test
    public void whenRightCommandFormatSentThenThereAreNoErrorsReported() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String help = Joiner.on(NL)
                .join(
                        "Format of a command is the following:",
                        "Key \"-d\" followed by a name of a dir where a file to be searched.",
                        "Key \"-n\" followed by a name of a file to be searched.",
                        "Key \"-m\" a file to be searched by a mask.",
                        "Key \"-f\" a file to be searched by a full name.",
                        "Key \"-o\" write result to a file followed by a name of a log file.",
                        "Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n",
                        "",
                        ""
                );
        new Search().printHelp();
        assertThat(out.toString(), is(help));


//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        File aux = new File(String.format("..%sauxiliary%s.%s", File.separator, File.separator, File.separator));
//        File auxCan = null;
//
//        try {
//            auxCan = aux.getCanonicalFile();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        String[] args = new String[]{"-d", auxCan.toString(), "-n", "*.txt", "-m", "-o", "log.txt"};
//        Search search;
//
//        try {
//
//            search = new Search();
//            search.start(args);

//            assertThat();
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }

    }
}
