package com.eden.parser.ast;

public interface Visitor {
    Object visitAssignmentNode(AssignmentNode a, Object param) throws Exception;
    Object visitIdentifierNode(IdentifierNode i, Object param) throws Exception;
    Object visitIntegerLiteralNode(IntegerLiteralNode i, Object param) throws Exception;
}
