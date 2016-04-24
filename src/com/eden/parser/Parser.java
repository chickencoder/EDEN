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

public class Parser {
    private Lexer lexer;

    public static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    }

    public Parser(Lexer lexer) throws Exception {
        lexer.lex();
        this.lexer = lexer;
    }

    public AssignmentNode parseAssignment() throws ParseError {
        // Position indicates the position of the current token
        Token t = this.lexer.nextToken();

        // Make sure t is 'let' keyword
        if (!t.getTokenType().equals(Token.TokenType.RESERVED)
                && !t.getTokenData().equals("let")) {
            throw new ParseError("Expected 'let' keyword at start of assignment");
        }

        // Then, create new IdentifierNode
        IdentifierNode ident = parseIdentifer();

        // Then, get next token
        t = this.lexer.nextToken();

        // Make sure t is an assignment operator
        if (!t.getTokenType().equals(Token.TokenType.ASSIGNMENT_OPERATOR)) {
            throw new ParseError("Expected an assignment operator after identifier in assignmnent.");
        }

        IntegerLiteralNode intlit = parseIntegerLiteral();

        // Then return AssignmentNode
        return new AssignmentNode(ident, intlit);
    }

    public IdentifierNode parseIdentifer() throws ParseError {
        Token t = this.lexer.nextToken();

        // Make sure t is an identifier
        if (!t.getTokenType().equals(Token.TokenType.IDENT)) {
            throw new ParseError("Expected an identifier after 'let' keyword in assignment.");
        }

        return new IdentifierNode(t.getTokenData());

    }

    public IntegerLiteralNode parseIntegerLiteral() throws ParseError {
        Token t = this.lexer.nextToken();

        // Make sure t is an integer literal
        if (!t.getTokenType().equals(Token.TokenType.INTEGER)) {
            throw new ParseError("Cannot assign the value" + t.getTokenType());
        }

        // Then, create new IntegerLiteralNode
        return new IntegerLiteralNode(t.getTokenData());
    }

    public AssignmentNode parse() throws ParseError {
        return parseAssignment();
    }
}
