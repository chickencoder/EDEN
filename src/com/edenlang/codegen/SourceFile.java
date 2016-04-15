package com.edenlang.codegen;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SourceFile class is responsible for organising
 * the multiple parts of a C source file.
 */
public class SourceFile {
    public class CodeLine {
        private StringBuilder builder = new StringBuilder();

        public CodeLine(int indentLevel, String code) {
            String indent = "";
            for (int i = 0; i < indentLevel;  i++) {
                indent += "  ";
            }
            builder.append(indent + code + "\n");
        }

        public String toString() {
            return this.builder.toString();
        }
    }

    private List<String> includes = new ArrayList<>();
    private List<CodeLine> main = new ArrayList<>();

    public SourceFile() {
        this.includes.add("stdio.h");
        this.main.add(new CodeLine(0, "int main() {"));
    }

    public void addLineToMain(int indentLevel, String line) {
        this.main.add(new CodeLine(indentLevel, line));
    }

    public String build() {
        StringBuilder file = new StringBuilder();
        file.append("/* Grown in the garden of EDEN */\n");
        for (String include: this.includes) {
            file.append("#include <" +  include + ">\n");
        }

        // Handle main function
        for (CodeLine line : this.main) {
            file.append(line.toString());
        }

        // Finish things up
        file.append("  return 0;\n");
        file.append("}");

        return file.toString();
    }
}
