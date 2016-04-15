package com.edenlang.codegen;

import com.edenlang.parser.ast.Node;
import com.edenlang.parser.ast.VarAssignmentNode;

import java.util.List;

/**
 * Generator class is responsible for generating a string
 * of C code from the transformed AST produced by the Transformer.
 */
public class Generator {
    private List<Node> ast;

    public Generator(List<Node> t_ast) {
        this.ast = t_ast;
    }

    public String generate() {
        SourceFile sourceBuilder = new SourceFile();

        for (int i = 0; i < this.ast.size(); i++) {

            // Generate integer assignment
            if (this.ast.get(i).getClass().getName().contains("VarAssignmentNode")) {
//                this.ast.get(i).get
                // This is where I'm stuck... I need to call methods on different classes
                // but how will I know which classes are which? :/
            }
        }

        return sourceBuilder.build();
    }
}
