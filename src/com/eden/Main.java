package com.eden;

import com.eden.generator.Generator;
import com.eden.lexer.Lexer;
import com.eden.parser.Parser;

public class Main {

    public static void main(String[] args) throws Exception {
        String program = "let i := 10";

        Parser parser = new Parser(new Lexer(program));
        Generator generator = new Generator(parser.parse());
        generator.generate();
        System.out.println(generator.getOutput());
    }
}
