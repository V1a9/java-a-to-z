package com.vgoryashko.checkPalindrome;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vlad Goryashko on 2/6/2017.
 */
public class Check {

    private String string = null;

    public Check(String aString) {
        this.string = aString;
    }

    public boolean checkPalindrome () {
        boolean result;
        Pattern pattern = Pattern.compile("[a-zA-z]{2}[a-zA-Z]{1}[a-zA-z]{2}[a-zA-Z]");
        if (string.length() != 5) {
            throw new InvalidWordException("Entered word consists less than 5 letters, try again.");
        } else {
            Matcher matcher = pattern.matcher(this.getString());
            if (matcher.matches()) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public String getString() {
        return this.string;
    }
}
