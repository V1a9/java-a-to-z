package com.vgoryashko.servlet.crudservlet;

/**
 * Class that perform validation of input from an User.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/27/17
 */
public class Validator {

    private String[] attributes;

    private boolean[] noErrors;

    public Validator(String... attributes) {
        this.attributes = attributes;
        this.noErrors = new boolean[attributes.length];
    }

    public boolean[] validate() {
        for (int i = 0; i < this.attributes.length; i++) {
            if (i == 0) {
                if (attributes[i].matches("^[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+$")) {
                    this.noErrors[i] = true;
                }
            } else if (i == 2) {
                if (attributes[i].length() > 3 && !attributes[i].matches("")) {
                    this.noErrors[i] = true;
                }
            } else if (i == 3) {
                if (attributes[i].matches("^[0-9a-zA-Z!_.,+]{3,}[^&$-<>?\"';: ^~`]$")) {
                    this.noErrors[i] = true;
                }
            } else if (i == 4) {
                if (attributes[i].matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
                    this.noErrors[i] = true;
                }
            } else if (i == 5 || i == 6) {
                if (!attributes[i].equals("")) {
                    this.noErrors[i] = true;
                }
            } else if (i == 7) {
                if (attributes[i].matches("^((0?[13578]|10|12)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1}))|(0?[2469]|11)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1})))$")) {
                    this.noErrors[i] = true;
                }
            }
        }
        return this.noErrors;
    }
}
