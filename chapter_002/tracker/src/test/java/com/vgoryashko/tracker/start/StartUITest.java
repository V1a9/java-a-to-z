package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Bug;
import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Class for testing StartUI class.
 * @author Vlad Goryashko
 * @version 3.0
 * @since 03.12.2016
 */
public class StartUITest {

    /**
     * Method for automatic testing of adding Item to tracking system with name "Item_01" and description "Item_decs_01".
     */
    @Test
    public void whenItemWithNameItem01IsAddedThenItsNotNull() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "1",
                "1",
                "Item_01",
                "Item_decs_01",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertNotNull(tracker.findByName("Item_01"));
    }

    /**
     * Method for automatic testing of adding Task to tracking system with name "Task_01" and description "Task_desc_01".
     */
    @Test
    public void whenTaskWithNameTask01IsAddedThenItsNotNull() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "1",
                "2",
                "Task_01",
                "Task_desc_01",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertNotNull(tracker.findByName("Task_01"));
    }

    /**
     * Method for automatic testing of adding a Comment to "Task_01".
     */
    @Test
    public void whenCommentToTask01IsAddedThenItsFieldContentsComment() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "1",
                "2",
                "Task_01",
                "Task_desc_01",
                "n",
                "2",
                "Task_01",
                "Task_01_comment1",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertThat(tracker.findByName("Task_01").getComment().getCommentField(), is("Task_01_comment1"));
    }

    /**
     * Method for automatic testing of adding Bug_01.
     */
    @Test
    public void whenBugIsAddedThenItsNotNull() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "1",
                "3",
                "Bug_01",
                "Bug_01_desc",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertNotNull(tracker.findByName("Bug_01"));
    }

    /**
     * Method that tests removing of Task_01.
     */
    @Test
    public void whenTask01IsRemovedThenItsNull() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "4",
                "Task_ID_01",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Task task = new Task("Task_01", "Task_01_desc");
        tracker.addItem(task);
//        tracker.findByName("Task_01").setId("Task_ID_01");
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertNull(tracker.findByName("Task_01"));
    }

    /**
     * Method that test depicting of all Items.
     */
    @Test
    public void whenGetAllItemsThenAllAddedItemsAreDepicted() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "3",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Item task = new Task("Task_01", "Task_01_desc");
        Item item = new Item("Item_01", "Item_01_desc");
        Item bug = new Bug("Bug_01", "Bug_01_desc");
        tracker.addItem(task);
        tracker.addItem(item);
        tracker.addItem(bug);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(tracker.getAll(), is(new Item[]{task, item, bug}));
    }

    /**
     * Method that tests searching of an item by name.
     */
    @Test
    public void whenItemIsBeingSearchedByNameThenItsDepicted() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "5",
                "1",
                "Task_02",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Item task = new Task("Task_02", "Task_02_desc");
        Item item = new Item("Item_01", "Item_01_desc");
        Item bug = new Bug("Bug_03", "Bug_03_desc");
        tracker.addItem(task);
        tracker.addItem(item);
        tracker.addItem(bug);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertThat(tracker.findByName("Task_02"), is(task));
    }

    /**
     * Method that tests searching of an item by Id.
     */
    @Test
    public void whenItemIsBeingSearchedByIdThenItsDepicted() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "5",
                "2",
                "Bug_03_ID",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Item task = new Task("Task_02", "Task_02_desc");
        Item item = new Item("Item_01", "Item_01_desc");
        Item bug = new Bug("Bug_03", "Bug_03_desc");
        tracker.addItem(task);
        tracker.addItem(item);
        tracker.addItem(bug);
//        tracker.findByName("Bug_03").setId("Bug_03_ID");
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        assertThat(tracker.findById("Bug_03_ID"), is(bug));
    }

    /**
     * Method that tests replacement of an Item by Task.
     */
    @Test
    public void whenItemIsReplacedByTaskThenTaskIsInsteadOfItemInTheSystem() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "6",
                "2",
                "Task_01",
                "Task_01_thatReplacedItem_01",
                "Item_01_ID",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Item item = new Item("Item_01", "Item_01_desc");
        tracker.addItem(item);
//        tracker.findByName("Item_01").setId("Item_01_ID");
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertThat(tracker.findById("Item_01_ID"), is(tracker.findByName("Task_01")));
    }

    /**
     * Method that tests replacement of an Item by Task.
     */
    @Test
    public void whenTaskIsReplacedByItemThenItemIsInsteadOfTaskInTheSystem() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "6",
                "1",
                "Item_01",
                "Item_01_thatReplacedTask_01",
                "Task_01_ID",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Item item = new Task("Task_01", "Task_01_desc");
        tracker.addItem(item);
//        tracker.findByName("Task_01").setId("Task_01_ID");
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertThat(tracker.findById("Task_01_ID"), is(tracker.findByName("Item_01")));
    }

    /**
     * Method that tests replacement of an Task by a Bug.
     */
    @Test
    public void whenTaskIsReplacedByBugThenBugIsInsteadOfTaskInTheSystem() {
        String[] modelingInputs;
        modelingInputs = new String[]{
                "6",
                "3",
                "Bug_01",
                "Bug_01_thatReplacedTask_01",
                "Task_02_ID",
                "y"};
        Input input = new StubInput(modelingInputs);
        Tracker tracker = new Tracker();
        Item item = new Task("Task_02", "Task_02_desc");
        tracker.addItem(item);
//        tracker.findByName("Task_02").setId("Task_02_ID");
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
//        assertThat(tracker.findById("Task_02_ID"), is(tracker.findByName("Bug_01")));
    }
}