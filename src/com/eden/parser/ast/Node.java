package com.eden.parser.ast;

import com.eden.lexer.Token;

import java.util.List;

public abstract class Node {
    public abstract Object visit(Visitor v, Object param) throws Exception;
}
