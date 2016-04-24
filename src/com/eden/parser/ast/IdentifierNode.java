package com.eden.parser.ast;

public class IdentifierNode extends Node {
    private String ident;

    public IdentifierNode(String identifier) {
        super();
        this.ident = identifier;
    }

    public String getIdent() {
        return this.ident;
    }

    public Object visit(Visitor v, Object param) throws Exception {
        return v.visitIdentifierNode(this, param);
    }
}
