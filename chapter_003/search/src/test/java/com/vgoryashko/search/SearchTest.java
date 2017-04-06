package com.vgoryashko.search;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class that test files' names to a file.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 4/3/17
 */
public class SearchTest {

    /**
     * Constant that stores new line string.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Constant that stores file separator string.
     */
    private static final String FS = File.separator;

    /**
     * Constant that stores path to auxiliary folder.
     */
    private final File aux = new File(String.format("..%sauxiliary%s.%s", FS, FS, FS));

    /**
     * Variable that stores canonical name of the aux folder.
     */
    private File auxCan = null;

    /**
     * Method that sets up tests environments.
     */
    @Before
    public void setupEnvironment() {
        try {
            this.auxCan = this.aux.getCanonicalFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Test search.
     */
    @Test
    public void searchTest() {

        String[] args = new String[]{"-d", this.auxCan.toString(), "-n", "*.txt", "-m", "-o", "log.txt"};
        try {
            Search.main(args);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Test that checks printHelp() method.
     */
    @Test
    public void whenPrintHelpInvokedThenHelpIsPrintedIntoConsole() {

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
    }

    /**
     * Test case for testing user input and its parsing by application.
     */
    @Test
    public void whenFileSearchByGlobExecutedThenExpectedFilesFoundAndResultRecorded() {

        String[] args = new String[]{"-d", this.auxCan.toString(), "-n", "ans*.txt", "-m", "-o", "log.txt"};
        String[] filesExpected = new String[]{
                "C:\\Private\\Projects\\java-a-to-z\\chapter_003\\auxiliary\\answers.txt",
                "C:\\Private\\Projects\\java-a-to-z\\chapter_003\\auxiliary\\answers_test.txt"
        };

        try {

            Search search = new Search();
            search.start(args);

            assertTrue(Files.exists(Paths.get(String.format(".%stmp", FS)), LinkOption.NOFOLLOW_LINKS));

            BufferedReader reader = new BufferedReader(new FileReader(String.format(".%stmp%slog.txt", FS, FS)));

            int filesCounter = 0;
            String file;

            do {
                file = reader.readLine();
                if (file != null) {
                    assertTrue(file.equals(filesExpected[filesCounter++]));
                }
            } while (file != null);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Test that checks that search file by full name is working correctly.
     */
    @Test
    public void whenFileSearchByNameExecutedThenExpectedFilesFoundAndResultDepictedInConsole() {

        String[] args = new String[]{"-d", this.auxCan.toString(), "-n", "inputMessages.txt", "-f"};

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));

            Search search = new Search();
            search.start(args);

            String output = Joiner.on(NL)
                    .join(
                            "Program that searches a file in a directory.",
                            "----------------------------------------------",
                            "Format of a command is the following:",
                            "Key \"-d\" followed by a name of a dir where a file to be searched.",
                            "Key \"-n\" followed by a name of a file to be searched.",
                            "Key \"-m\" a file to be searched by a mask.",
                            "Key \"-f\" a file to be searched by a full name.",
                            "Key \"-o\" write result to a file followed by a name of a log file.",
                            "Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n",
                            "",
                            "C:\\Private\\Projects\\java-a-to-z\\chapter_003\\auxiliary\\inputMessages.txt",
                            ""
                    );

            assertThat(out.toString(), is(output));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Test that checks WriteLog class.
     */
    @Test
    public void whenWriteLogInvokedThenCorrectLogCreated() {

        File filesExpected = new File(String.format("..%sauxiliary%sanswers.txt.", FS, FS));
        Path logFile = Paths.get(String.format("..%sauxiliary%ssearchLog.txt.%s", FS, FS, FS));

        try {
            Files.deleteIfExists(logFile);

            File filesExpectedCan = filesExpected.getCanonicalFile();

            Files.createFile(logFile);

            Path searchFile = Paths.get(String.format("..%sauxiliary%sanswers.txt.%s", FS, FS, FS));
            WriteLog writeLog = new WriteLog(logFile, searchFile);
            writeLog.write();

            assertTrue(Files.exists(logFile));

            BufferedReader reader = new BufferedReader(new FileReader(String.format(".%stmp%slog.txt", FS, FS)));

            String file = reader.readLine();

            assertTrue(file.equals(filesExpectedCan.toString()));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
