package org.preprocess;

import java.util.ArrayList;
import java.util.List;

import org.factory.NodeFactory;
import org.visitor.Node;

public class Parser {

    private final List<String> tokens;
    private int position = 0;
    private final NodeFactory factory;

    public Parser(List<String> tokens, NodeFactory factory) {
        this.tokens = tokens;
        this.factory = factory;
    }

    public Node parse() {

        if (position >= tokens.size()) {
            throw new RuntimeException("Unexpected end of input");
        }

        String token = tokens.get(position++);

        // list
        if ("(".equals(token)) {
            List<Node> list = new ArrayList<>();

            while (true) {
                if (position >= tokens.size()) {
                    throw new RuntimeException("Missing ')'");
                }

                if (")".equals(tokens.get(position))) {
                    position++; // skip ')'
                    break;
                }

                list.add(parse());
            }
            return factory.createList(list);
        }

        // extra )
        if (")".equals(token)) {
            throw new RuntimeException("Unexpected ')'");
        }

        // number checking
        if (token.matches("-?\\d+(\\.\\d+)?")) {
            try {
                boolean isFloatLiteral = token.contains(".");
                return factory.createNumber(Double.parseDouble(token), isFloatLiteral);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number: " + token);
            }
        }

        return factory.createSymbol(token);
    }

    public boolean hasRemainingTokens() {
        return position < tokens.size();
    }

    public String getCurrentToken() {
        return position < tokens.size() ? tokens.get(position) : null;
    }

}
