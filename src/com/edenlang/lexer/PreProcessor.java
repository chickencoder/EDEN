package com.edenlang.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PreProcessor is responsible for handling all of
 * the compiler directives (lines beginning with %)
 * and removing them to complete the source code.
 */
public class PreProcessor {
    private String source;

    private final static Pattern directive = Pattern.compile("\\%([a-z]+)[\\s]+\\'([a-zA-Z09]+)\\'[\\s]*");

    /**
     * PreProcess code string, return
     * processed string.
     */
    public static String process(String input) {
        // Handle include directives
        Matcher m = directive.matcher(input);
        String instruction = "";
        String value = "";

        if (m.matches()) {
            instruction = m.group(1);
            value = m.group(2);
        }

        if (instruction.equals("include")) {
            // Remove directive from source
            input = input.replaceAll("\\%([a-z]+)[\\s]+\\'([a-zA-Z09]+)\\'[\\s]*", "");

            // Open file reader to read file
            String s = "# included the file" + value;
            input = s += input;
        }

        return input;
    }
}
