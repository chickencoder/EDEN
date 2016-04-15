package com.edenlang.lexer;

public class Token {

    public enum TokenType {
        INTEGER,
        STRING,
        VOID,
        BOOLEAN,
        FLOAT,
        BINARY_OPERATOR,
        COMPARISON_OPERATOR,
        LOGIC_OPERATOR,
        ASSIGNMENT_OPERATOR,
        PAREN,
        IDENT,
        RESERVED,
    }

    private TokenType token_type;
    private String token_data;

    public Token(TokenType t, String d) {
        this.token_type = t;
        this.token_data = d;
    }

    public TokenType getTokenType() {
        return this.token_type;
    }

    public String getTokenData() {
        return this.token_data;
    }

    public void prettyPrintToken() {
        System.out.println("Type: " + String.valueOf(this.token_type) + " | Data: " + this.token_data);
    }
}
