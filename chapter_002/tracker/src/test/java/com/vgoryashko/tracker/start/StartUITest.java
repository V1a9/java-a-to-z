package com.vgoryashko.tracker.start;

import org.junit.Ignore;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import com.vgoryashko.tracker.models.Item;

/**
 * Class for testing StartUI class.
 * @author Vlad Goryashko
 * @version 1.0
 * @since 11.21.2016
 */
public class StartUITest {

    /**
     * Method for automatic testing of UI through modeling of an static user inputs.
     */
    @Test
    public void stubInputTest() {
        String[] modelingInputs;
        Tracker tracker = new Tracker();
        modelingInputs = new String[]{
                                        "1",
                                        "1",
                                        "11",
                                        "111"};
        Input input = new StubInput(modelingInputs);
        StartUI startUI = new StartUI(input);
        startUI.init();
        assertThat(tracker.getAll(), is(new Item[]{null}));
    }
}
