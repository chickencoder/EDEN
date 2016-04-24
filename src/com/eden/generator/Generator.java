package com.eden.generator;

import com.eden.parser.ast.AssignmentNode;
import com.eden.parser.ast.IdentifierNode;
import com.eden.parser.ast.IntegerLiteralNode;
import com.eden.parser.ast.Node;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.Visitor;

import java.util.List;

/**
 * Generator class is responsible for generating a string
 * of C code from the transformed AST produced by the Transformer.
 */
public class Generator implements Visitor {
    public static class CompileTimeError extends Exception {
        public CompileTimeError(String msg) {
            super(msg);
        }
    }

    public Object visitAssingment(AssignmentNode a, Object param) throws Exception {
        return null;
    }

    public Object visitIdentifierNode(IdentifierNode i, Object param) throws Exception {
        return null;
    }

    public Object visitIntegerLiteralNode(IntegerLiteralNode a, Object param) throws Exception {
        return null;
    }
}
