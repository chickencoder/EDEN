package com.eden.parser.ast;

public class AssignmentNode extends Node {
    private IdentifierNode ident;
    private IntegerLiteralNode value;

    public AssignmentNode(IdentifierNode ident, IntegerLiteralNode value) {
        super();
        this.ident = ident;
        this.value = value;
    }

    public IdentifierNode getIdent() {
        return this.ident;
    }

    public IntegerLiteralNode getIntegerLiteral() {
        return this.value;
    }

    public Object visit(Visitor v, Object param) throws Exception {
        return v.visitAssignmentNode(this, param);
    }
}
