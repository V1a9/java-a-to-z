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
 * @version 1.1
 * @since 4/9/17
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
     * Test case for testing user input and its parsing by application.
     */
    @Test
    public void whenFileSearchByGlobExecutedThenExpectedFilesFoundAndResultRecorded() {

        String[] args = new String[]{"-d", this.auxCan.toString(), "-n", "answers.*", "-m", "-o", "log.txt"};
        String[] filesExpected = new String[]{
                String.format("%s%s%s", this.auxCan.toString(), FS, "answers.txt")
        };

        try {

            Search search = new Search();
            KeysValidator keysValidator = new KeysValidator(args);

            if (keysValidator.validate()) {
                search.start(args);
            }

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
            KeysValidator keysValidator = new KeysValidator(args);

            if (keysValidator.validate()) {
                search.start(args);
            }

            String output = Joiner.on(NL)
                    .join(
                            String.format("%s%s%s", this.auxCan.toString(), FS, "inputMessages.txt"),
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

            Files.createFile(logFile);

            Path searchFile = Paths.get(String.format("..%sauxiliary%sanswers.txt.%s", FS, FS, FS));
            WriteLog writeLog = new WriteLog(logFile, searchFile);
            writeLog.write();

            assertTrue(Files.exists(logFile));

            BufferedReader reader = new BufferedReader(new FileReader(String.format("..%sauxiliary%ssearchLog.txt.%s", FS, FS, FS)));

            String file = reader.readLine();

            assertTrue(file.equals(filesExpected.toString()));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Test which validates of user input using the -f key.
     */
    @Test
    public void whenWrongKeyEnteredInsteadOfFThenError() {

        String[] args = new String[]{"-d", this.auxCan.toString(), "-n", "inputMessages.txt", "-d"};

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));

            Search search = new Search();
            KeysValidator keysValidator = new KeysValidator(args);

            if (keysValidator.validate()) {
                search.start(args);
            }

            String output = Joiner.on(NL)
                    .join(
                            "Mistake in the command format - third key must by \"-m\" or \"-f\". Try again.",
                            "Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n",
                            "\"-d\" - a search directory;",
                            "\"-n\" - a name of a file to be found;",
                            "\"-m\" - find by a mask;",
                            "\"-f\" - find by exact name;",
                            "\"-o\" - optional parameter write log to a file (followed by a log file name).",
                            ""
                    );

            assertThat(out.toString(), is(output));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
