package com.eden.parser.ast;

public class ParenNode extends Node {
    private String value;

    public ParenNode(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void visit() {

    }
}
