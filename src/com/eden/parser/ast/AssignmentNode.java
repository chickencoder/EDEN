package com.eden.parser.ast;

public class AssignmentNode extends Node {
    private Node type;
    private Node ident;
    private Node value;

    public AssignmentNode(Node type, Node ident, Node value) {
        super();
        this.type = type;
        this.ident = ident;
        this.value = value;
    }

    public void visit() { }
}
