package com.eden.parser.ast;

public class StringLiteralNode extends Node {
    private String value;

    public StringLiteralNode(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void visit() {}
}
