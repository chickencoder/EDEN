package com.eden.generator;

import com.eden.parser.Parser;
import com.eden.parser.ast.Node;

import java.util.List;

/**
 * Generator class is responsible for generating a string
 * of C code from the transformed AST produced by the Transformer.
 */
public class Generator {
    public static class CompileTimeError extends Exception {
        public CompileTimeError(String msg) {
            super(msg);
        }
    }

    private List<Node> ast;

    public Generator(List<Node> t_ast) {
        this.ast = t_ast;
    }

    public String generate() throws CompileTimeError {
        SourceFile sourceBuilder = new SourceFile();

        for (Node node: this.ast) {
            // Generate VarAssignments
            if (node.getNodeType().equals("VarAssignment")) {
                String ident = node.getChildren().get(0).getNodeValue();
                String value = "";
                String type = "";

                // Determine variable type & set indent, value
                if (node.getChildren().get(1).getNodeType().equals("StringLiteral")) {
                    ident = "*" + ident;
                    value = "\"" + node.getChildren().get(1).getNodeValue() + "\"";
                    type = "char";
                } else if (node.getChildren().get(1).getNodeType().equals("IntegerLiteral")) {
                    type = "int";
                    value = node.getChildren().get(1).getNodeValue();
                } else {
                    throw new CompileTimeError("Cannot assign variable " + ident);
                }

                sourceBuilder.addLineToMain(1, type + " " + ident + " = " + value + ";");
            }

            // Generate Print calls
            if (node.getNodeType().equals("PrintCall")) {
                String value = "";
                if (node.getChildren().get(0).getNodeType().equals("IntegerLiteral")) {
                    value = "\"%d\"" + ", " + node.getChildren().get(0).getNodeValue();
                } else if (node.getChildren().get(0).getNodeType().equals("StringLiteral")) {
                    value = "\"" + node.getChildren().get(0).getNodeValue() + "\"";
                } else if (node.getChildren().get(0).getNodeType().equals("Identifier")) {
                    value = node.getChildren().get(0).getNodeValue();
                }

                sourceBuilder.addLineToMain(1, "printf(" + value + "\");");
            }
        }

        return sourceBuilder.build();
    }
}
