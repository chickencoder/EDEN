package com.edenlang.parser.ast;

public class VarAssignmentNode extends Node {
    private Node identifier;
    private Node value;

    public VarAssignmentNode(Node ident, Node value) {
        super(value.getNodeValue());
        this.identifier = ident;
        this.value = value;
    }

    public Node getIdentifier() {
        return this.identifier;
    }
}
