package com.edenlang;

import com.edenlang.codegen.Generator;
import com.edenlang.fileUtils.FileWriter;
import com.edenlang.lexer.Lexer;
import com.edenlang.lexer.Token;
import com.edenlang.parser.Parser;
import com.edenlang.parser.Transformer;
import com.edenlang.parser.ast.Node;
import com.edenlang.fileUtils.FileReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader f = new FileReader("C:\\Users\\CCF Youth\\IdeaProjects\\EDEN\\examples\\welcome.eden");
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
        String output = g.generate();
        System.out.print(output);

        // Write output to C file
        String outputFilename = f.filenameWithoutExt();
        FileWriter w = new FileWriter("C:\\Users\\CCF Youth\\IdeaProjects\\EDEN\\examples\\outs\\" + outputFilename + ".c");
        w.writeFile(output);

        // Compile File
//        String command = "g++ " + w.getPath() + " -o " + "C:\\Users\\CCF Youth\\IdeaProjects\\EDEN\\examples\\outs\\" + outputFilename;
//        Process compile = Runtime.getRuntime().exec("g++ " + w.getPath() + " -o " + "C:\\Users\\CCF Youth\\IdeaProjects\\EDEN\\examples\\outs\\" + outputFilename);
//        System.out.println(command);
    }
}
