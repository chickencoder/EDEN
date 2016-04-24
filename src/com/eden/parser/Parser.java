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
    private int cursor = 0;
    private Lexer lexer;
    private List<Node> ast = new ArrayList<>();

    public static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    }

    public Parser(Lexer l) throws Exception {
        l.lex();
        this.lexer = l;
    }

    private Node walk() throws ParseError {
        Token token = this.lexer.nextToken();

        if (token == null)
            return null;

        if (token.getTokenType().equals(Token.TokenType.INTEGER)) {
            this.cursor++;
            return new IntegerLiteralNode(token.getTokenData());

        } else if(token.getTokenType().equals(Token.TokenType.IDENT)) {
            this.cursor++;
            return new IdentifierNode(token.getTokenData());

        } else if(token.getTokenType().equals(Token.TokenType.ASSIGNMENT_OPERATOR)) {
            this.cursor++;
            return new AssignmentOperatorNode();

        } else if (token.getTokenType().equals(Token.TokenType.RESERVED)
                    && (token.getTokenData().equals("int")) || token.getTokenData().equals("str")) {
                // Create type node
                TypeNode type = new TypeNode(token.getTokenData());

                this.cursor++;

                // Walk to find indentifier
                Node ident = walk();
                // Make sure next token is an identifier
                if (!ident.getClass().equals(IdentifierNode.class)) {
                    throw new ParseError("Expected identifier in assignment statement. Instead got: " + token.getTokenData());
                }

                Node astk = walk();
                // Make sure next token is an assignment token
                if (!astk.getClass().equals(AssignmentOperatorNode.class)) {
                    throw new ParseError("Expected assignment operator in assignment statement. Instead got: " + token.getTokenData());
                }

                Node value = walk();
                // Check that value is either a literal or identifier
                if (!value.getClass().equals(IntegerLiteralNode.class)) {
                    throw new ParseError("Expected a literal or identifier in assignment statement. Instead got: " + token.getTokenData());
                }

                return new AssignmentNode(type, ident, value);

        } else if (token.getTokenType().equals(Token.TokenType.PAREN)) {
            this.cursor++;
            return new ParenNode(token.getTokenData());

        } else if (token.getTokenType().equals(Token.TokenType.STRING)) {
            this.cursor++;
            return new StringLiteralNode(token.getTokenData());

        } else {
            throw new ParseError("Error parsing token: " + token.getTokenData());
        }

    }

    /**
     * Returns AST
     */
    public List<Node> parse() throws ParseError {

        while (true) {
            Node n = walk();
            if (n != null) {
                this.ast.add(n);
            } else {
                break;
            }
        }

        return this.ast;
    }
}
