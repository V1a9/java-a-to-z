package com.vgoryashko.interactivecalc;

/**
 * Class that implements a menu for the application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public class Menu {

    /**
     * Variable that stores menu elements.
     */
    private UserAction[] actions = new UserAction[]{};

    /**
     * Variable that is used for operating with Calculator.
     */
    private Calculator calculator;

    /**
     * Variable that is used for operating with Input.
     */
    private IO input;

    /**
     * Constructor for the class.
     *
     * @param aCalculator object to be used for calculating operations.
     * @param aInput IO system to be used for interaction with an end-user.
     */
    public Menu(Calculator aCalculator, IO aInput) {
        this.calculator = aCalculator;
        this.input = aInput;
    }

    /**
     * Method that selects an operation based on a key (assigned to each one).

     * @param key                               value assigned to each operation in the menu.
     */
    public void selectOperation(int key) {
        this.actions[key - 1].execute(this.input, this.calculator);
    }

    /**
     * Method that prints menu.
     */
    public void showMenu() {
        for (UserAction action : actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}
