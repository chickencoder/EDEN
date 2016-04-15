package com.edenlang.parser.ast;

public class PrintCallNode extends Node {
    private Node expression;

    public PrintCallNode(Node expression) {
        super(expression.getNodeValue());
        this.expression = expression;
    }
}
