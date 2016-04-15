package com.edenlang.parser;

import com.edenlang.parser.ast.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The tranformer object is responsible for
 * taking EDEN syntax AST and producing an
 * AST that better-represents the C language.
 */
public class Transformer {
    private List<Node> ast;

    public Transformer(List<Node> ast) {
        this.ast = ast;
    }

    public List<Node> transform () {
        return this.ast;
    }
}
