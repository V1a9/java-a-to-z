package com.vgoryashko.sql.collectfromsqlru;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that tests whether a post contains a position for a Java dev.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/20/17
 */
public class TestMatch {

    private Matcher matcher;

    private String advertText;

    public TestMatch(String advertText) {

        this.advertText = advertText;

    }

    public boolean test(Pattern pattern) {

        boolean result = false;

        this.matcher = pattern.matcher(this.advertText);

        if (this.matcher.find()) {
            result = true;
        }

        return result;

    }

}
