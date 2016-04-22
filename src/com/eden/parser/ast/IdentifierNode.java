package com.eden.parser.ast;

public class IdentifierNode extends Node {
    private String ident;

    public IdentifierNode(String identifier) {
        super();
        this.ident = identifier;
    }

    public void visit() { }
}
