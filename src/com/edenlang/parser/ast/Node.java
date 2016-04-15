package com.edenlang.parser.ast;

import com.edenlang.lexer.Token;

public class Node {
    private String value;

    public Node(String value) {
        this.value = value;
    }

    public String getNodeValue() {
        return this.value;
    }
}
