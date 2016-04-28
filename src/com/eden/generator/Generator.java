package com.eden.generator;

import com.eden.parser.ast.*;

/**
 * Generator class is responsible for generating a string
 * of C code from the transformed AST produced by the Transformer.
 */
public class Generator implements Visitor {
    private SourceFile sf;
    private AssignmentNode root;

    public static class CompileTimeError extends Exception {
        public CompileTimeError(String msg) {
            super(msg);
        }
    }

    public Generator(AssignmentNode root) {
        this.sf = new SourceFile();
        this.root = root;
    }

    public Object visitAssignmentNode(AssignmentNode a, Object param) throws Exception {
        String ln = "int " + (String)(a.getIdent().visit(this, param)) + " = " + (String)(a.getIntegerLiteral().visit(this, param)) + ";";
        this.sf.addLineToMain(1, ln);
        return null;
    }

    public Object visitIdentifierNode(IdentifierNode i, Object param) throws Exception {
        return i.getIdent();
    }

    public Object visitIntegerLiteralNode(IntegerLiteralNode a, Object param) throws Exception {
        return a.getValue();
    }

    public void generate() throws Exception {
        this.visitAssignmentNode(this.root, new Object());
    }

    public String getOutput() {
        return this.sf.build();
    }
}
