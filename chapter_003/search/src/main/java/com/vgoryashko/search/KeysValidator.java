package com.vgoryashko.search;

import com.google.common.base.Joiner;

/**
 * Class that validates keys for the file search application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/9/17
 */
public class KeysValidator {

    /**
     * Variable that referring to a an array of input string from an end-user.
     */
    private String[] args;

    /**
     * Constructor for the class.
     *
     * @param aArgs                 array of strings that represents an user command
     */
    public KeysValidator(String[] aArgs) {
        this.args = aArgs;
    }

    /**
     * Variable that stores hints for the command format.
     */
    private String hint = Joiner.on(
            System.getProperty("line.separator")).join(
            "Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n",
            "\"-d\" - a search directory;",
            "\"-n\" - a name of a file to be found;",
            "\"-m\" - find by a mask;",
            "\"-f\" - find by exact name;",
            "\"-o\" - optional parameter write log to a file (followed by a log file name)."
    );

    /**
     * Method that validates keys.
     *
     * @return                              <code>boolean</code>
     */
    public boolean validate() {
        boolean result = true;
        if (this.args[0] != null && !"-d".equals(this.args[0])) {
            System.out.println("Mistake in the command format - first key must by \"-d\". Try again.");
            System.out.println(this.hint);
            result = false;
        } else if (this.args[2] != null && !"-n".equals(this.args[2])) {
            System.out.println("Mistake in the command format - second key must by \"-n\". Try again.");
            System.out.println(this.hint);
            result = false;
        } else if ((this.args[4] != null && !"-m".equals(this.args[4]) && !"-f".equals(this.args[4]))) {
            System.out.println("Mistake in the command format - third key must by \"-m\" or \"-f\". Try again.");
            System.out.println(this.hint);
            result = false;
        } else if (this.args.length > 5 && !"-o".equals(this.args[5])) {
            System.out.println("Mistake in the command format - forth key must by \"-o\" or empty (is optional). Try again.");
            System.out.println(this.hint);
            result = false;
        }
        return result;
    }
}
