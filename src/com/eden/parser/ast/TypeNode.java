package com.eden.parser.ast;

public class TypeNode extends Node {
    private String type;

    public TypeNode(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void visit() {}
}
