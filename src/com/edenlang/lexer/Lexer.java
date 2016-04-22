package com.edenlang.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Hand Built Scanner & Lexer for the
 * EDEN programming language written by
 * Jesse Sibley & inspired by Jai, Go,
 * Pascal, ALGOL & Lua.
 */


public class Lexer {
    private String buffer = "";
    private int position = 0;
    private List<Token> tokens = new ArrayList<>();
    private int token_location = 0;

    private Pattern number = Pattern.compile("[0-9]");
    private Pattern letter = Pattern.compile("[a-zA-Z]");
    private Pattern wordchar = Pattern.compile("[-!$%^&£*()_+|~=`{}\\[\\]:;<>?,.\\/\\w]");
    private Pattern operator_symbol = Pattern.compile("[-=*\\/|&:=><!&|]");
    private Pattern whitespace = Pattern.compile("[\\s]");

    public Lexer(String source) {
        this.buffer = PreProcessor.process(source);
    }

    public boolean charIsInteger(String c) {
        return number.matcher(c).matches();
    }

    public boolean charIsLetter(String c) {
        return letter.matcher(c).matches();
    }

    /**
     * Eden's definition of a wordchar
     * is a literal wordchar or a common
     * ASCII symbol
     */
    public boolean charIsWordChar(String c) {
        return wordchar.matcher(c).matches();
    }


    public boolean charIsSpace(String c) {
        return c.equals(" ");
    }

    public final String[] reserves = {
            "let",
            "func",
            "if",
            "then",
            "else",
            "end",
            "for",
            "do",
            "print",
            "return"
    };

    public boolean isTokenReserved(String token) {
        return Arrays.asList(reserves).contains(token);
    }

    public final String[] binary_operators = {
        "+", "-", "*", "/"
    };

    public final String[] comparison_operators = {
            "==", "!=", "<", ">", "<=", ">="
    };

    public final String[] logic_operators = {
            "&&", "||"
    };


    public boolean charIsOperatorSymbol(String c) {
        return operator_symbol.matcher(c).matches();
    }

    public boolean charIsWhitespace(String c) {
        return whitespace.matcher(c).matches();
    }

    public Token.TokenType identifyOperatorType(String op) throws Exception {
        op = op.trim();
        if (Arrays.asList(binary_operators).contains(op)) {
            return Token.TokenType.BINARY_OPERATOR;
        } else if (Arrays.asList(comparison_operators).contains(op)) {
            return Token.TokenType.COMPARISON_OPERATOR;
        } else if (Arrays.asList(logic_operators).contains(op)) {
            return Token.TokenType.LOGIC_OPERATOR;
        } else if (op.equals(":=") || op.equals(":")) {
            return Token.TokenType.ASSIGNMENT_OPERATOR;
        } else {
            throw new Exception("Unrecognised operator: " + op);
        }
    }

    public void lex() throws Exception {
        /**
         * The Lexer tests for each of the
         * following token types in this
         * order:
         *  * INTEGER [☺]
         *  * STRING  [☺]
         *  * VOID    [☺]
         *  * BOOLEAN [☺]
         *  * FLOAT   [Currently Not implemented]
         *  * BINARY OPERATOR      [☺]
         *  * COMPARISON OPERATOR  [☺]
         *  * LOGIC OPERATOR       [☺]
         *  * ASSIGNMENT OPERATOR  [☺]
         *  * PAREN      [☺]
         *  * IDENTIFIER [☺]
         *  * RESERVED   [☺]
         */

        List<Token> tokens = new ArrayList<>();

        // Handle comments
        this.buffer = this.buffer.replaceAll("#[\\s\\w]+\\n", " ");

        while (this.position < this.buffer.length()) {
            String ch = String.valueOf(this.buffer.charAt(this.position));

            if (charIsInteger(ch)) {
                String tk = "";

                while (charIsInteger(ch) && this.position < this.buffer.length()) {
                    tk += ch;
                    this.position++;
                    if (this.position != this.buffer.length())
                        ch = String.valueOf(this.buffer.charAt(this.position));
                }

                tokens.add(new Token(Token.TokenType.INTEGER, tk));
                continue;
            }

            if (ch.equals("'")) {
                // Is next char a letter/number? or a space? or another quote?
                this.position++;
                String tk = "";

                while (ch != "'" && this.position < this.buffer.length()) {
                    ch = String.valueOf(this.buffer.charAt(this.position));
                    if (ch.equals("'")) {
                        break;
                    } else if (charIsWordChar(ch) || charIsSpace(ch)) {
                        tk += ch;
                        this.position++;
                        continue;
                    } else {
                        break;
                    }
                }

                tokens.add(new Token(Token.TokenType.STRING, tk));
                this.position++;
                continue;
            }

            /** Identifiers & Keywords **/
            if (charIsLetter(ch)) {
                String tk = "";

                while (charIsLetter(ch) && this.position < this.buffer.length()) {
                    tk += ch;
                    this.position++;
                    if (this.position != this.buffer.length())
                        ch = String.valueOf(this.buffer.charAt(this.position));
                }

                tk = tk.trim(); // Remove whitespace

                // Is char ident or keyword?
                if (isTokenReserved(tk)) {
                    // Determine keyword
                    if (tk.equals("void")) {
                        tokens.add(new Token(Token.TokenType.VOID, tk));
                    } else if (tk.equals("true") || tk.equals("false")) {
                        tokens.add(new Token(Token.TokenType.BOOLEAN, tk));
                    } else {
                        tokens.add(new Token(Token.TokenType.RESERVED, tk));
                    }
                } else {
                    tokens.add(new Token(Token.TokenType.IDENT, tk));
                }

                continue;
            }

            // Handle operators
            if (charIsOperatorSymbol(ch)) {
                String tk = "";

                while (charIsOperatorSymbol(ch) && this.position < this.buffer.length()) {
                    tk += ch;
                    this.position++;
                    if (this.position != this.buffer.length())
                        ch = String.valueOf(this.buffer.charAt(this.position));
                }

                // Identify operator type
                Token.TokenType type = identifyOperatorType(tk);
                tokens.add(new Token(type, tk));
                continue;
            }

            // Handle whitespace
            if (charIsWhitespace(ch)) {
                this.position++;
                continue;
            }

            if (ch.equals("(") || ch.equals(")")) {
                tokens.add(new Token(Token.TokenType.PAREN, ch));
                this.position++;
                continue;
            } else {
                throw new Exception("Unrecognised token: " + ch);
            }
        }
        this.tokens = tokens;
    }

    public Token nextToken() {
        return this.tokens.get(this.token_location++);
    }
}
