package org.example;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final List<String> tokens;
    private int index = 0;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    public Node parse() {

        String token = tokens.get(index++);

        if (token.equals("(")) {
            List<Node> elements = new ArrayList<>();

            while (!tokens.get(index).equals(")")) {
                elements.add(parse());
            }
            index++; // skip ")"
            return new ListNode(elements);
        }

        if (token.matches("\\d+")) {
            return new NumberNode(Integer.parseInt(token));
        }

        return new SymbolNode(token);
    }
}

