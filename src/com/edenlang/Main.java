package com.edenlang;

import com.edenlang.codegen.Generator;
import com.edenlang.lexer.Lexer;
import com.edenlang.lexer.Token;
import com.edenlang.parser.Parser;
import com.edenlang.parser.Transformer;
import com.edenlang.parser.ast.Node;
import com.edenlang.reader.FileReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader f = new FileReader("C:\\Users\\CCF Youth\\IdeaProjects\\EDEN\\examples\\assignment.eden");
        String buffer = f.readFully();

        Lexer l = new Lexer(buffer);
        List<Token> tokens = l.lex();
//        for (int i = 0; i < tokens.size(); i++) {
//            tokens.get(i).prettyPrintToken();
//        }

        Parser p = new Parser(tokens);
        List<Node> ast = p.parse();

        // Transformer
        Transformer t = new Transformer(ast);
        List<Node> t_ast = t.transform();

        // Code Generator
        Generator g = new Generator(t_ast);
        System.out.print(g.generate());
    }
}
