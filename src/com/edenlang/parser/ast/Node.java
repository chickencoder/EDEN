package com.edenlang.parser.ast;

import com.edenlang.lexer.Token;

import java.util.List;

public abstract class Node implements Visitable {
    private String type;
    private String value;
    private List<Node> children;

    public Node(String type, String value, List<Node> children) {
        this.type = type;
        this.value = value;
        this.children = children;
    }

    public String getNodeType() {
        return this.type;
    }

    public String getNodeValue() {
        return this.value;
    }

    public List<Node> getChildren() {
        return this.children;
    }
}
