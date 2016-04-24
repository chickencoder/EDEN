package com.eden.parser;

import com.eden.lexer.Lexer;
import com.eden.lexer.Token;
import com.eden.parser.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand built parser for the EDEN
 * programming language.
 */

public class ParserClone {
    private int cursor = 0;
    private Lexer lexer;

    public static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    }

    public ParserClone(Lexer lexer) throws Exception {
        lexer.lex();
        this.lexer = lexer;
    }

    public AssignmentNode parseAssignment(int position) {
        //
    }

    public List<Node> parse() throws ParseError {
        parseAssignment(this.cursor);
    }
}
