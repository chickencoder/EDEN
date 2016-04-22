package com.eden.parser.ast;

public class IntegerLiteralNode extends Node {
    private String value;

    public IntegerLiteralNode(String value) {
        super();
        this.value = value;
    }

    public void visit() {}
}
