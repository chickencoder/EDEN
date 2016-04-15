package com.edenlang.parser;

import com.edenlang.lexer.Token;
import com.edenlang.parser.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand built parser for the EDEN
 * programming language.
 */

public class Parser {
    private int cursor = 0;
    private List<Token> tokens;
    private List<Node> ast = new ArrayList<>();

    public static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    }

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Node walk() throws ParseError {
        Token token;

        try {
            token = this.tokens.get(this.cursor);
        } catch (IndexOutOfBoundsException e) {
            return new EOFNode();
        }

        if (token.getTokenType().equals(Token.TokenType.INTEGER)) {
            this.cursor++;
            return new IntegerNode(token.getTokenData());

        } else if(token.getTokenType().equals(Token.TokenType.IDENT)) {
            this.cursor++;
            return new Node(token.getTokenData());

        } else if(token.getTokenType().equals(Token.TokenType.ASSIGNMENT_OPERATOR)) {
            this.cursor++;
            return new Node(token.getTokenData());

        } else if (token.getTokenType().equals(Token.TokenType.RESERVED)
                    && token.getTokenData().equals("let")) {
                this.cursor++;
                // Walk to find indentifier
                Node ident = walk();

                // Walk over assignment token
                walk();
                Node value = walk();

                return new VarAssignmentNode(ident, value);
        } else if (token.getTokenType().equals(Token.TokenType.PAREN)) {
            this.cursor++;
            return new ParenNode(token.getTokenData());
        }
        else if (token.getTokenType().equals(Token.TokenType.RESERVED)
                    && token.getTokenData().equals("print")) {
            this.cursor++;
            // Check next token is open paren
            Node paren = walk();
            if (paren.getNodeValue().equals("(")) {
                Node expression = walk();

                // Build PrintCallNode
                return new PrintCallNode(expression);
            } else {
                throw new ParseError("Expected ( after print statement call. Instead got: " + paren.getNodeValue());
            }

        } else {
            throw new ParseError("Error parsing token: " + token.getTokenData());
        }

    }

    /**
     * Returns AST
     */
    public List<Node> parse() throws ParseError {
        for (int i = 0; i < this.tokens.size(); i++) {
            Node n = walk();
            if (n.getNodeValue().equals("EOF")) {
                break;
            } else {
                this.ast.add(n);
            }
        }

        return this.ast;
    }

    public void prettyPrintAST() {
        for (int i = 0; i < this.ast.size(); i++) {
            System.out.println(this.ast.get(i).getNodeValue());
        }
    }
}
