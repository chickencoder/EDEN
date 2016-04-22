package com.edenlang;

import com.edenlang.codegen.Generator;
import com.edenlang.fileUtils.FileWriter;
import com.edenlang.lexer.Lexer;
import com.edenlang.lexer.PreProcessor;
import com.edenlang.lexer.Token;
import com.edenlang.parser.Parser;
import com.edenlang.parser.Transformer;
import com.edenlang.parser.ast.Node;
import com.edenlang.fileUtils.FileReader;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader f = new FileReader("C:\\Users\\CCF Youth\\IdeaProjects\\EDEN\\examples\\hello.eden");
        String buffer = f.readFully();

        PreProcessor.process("%include 'std'");

        Lexer l = new Lexer(buffer);
        Parser p = new Parser(l);
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
