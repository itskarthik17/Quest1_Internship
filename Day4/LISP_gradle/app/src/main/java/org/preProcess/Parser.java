package org.preProcess;

import java.util.ArrayList;
import java.util.List;

import org.factory.NodeFactory;
import org.visitor.Node;

public class Parser {
    public List<String> tokens;
    public int position = 0;
    public NodeFactory factory;

    public Parser(List<String> tokens, NodeFactory factory) {
        this.tokens = tokens;
        this.factory = factory;
    }

    public Node parse() {
        String token = tokens.get(position++);

        if ("(".equals(token)) {
            List<Node> list = new ArrayList<>();
            while (!")".equals(tokens.get(position))) {
                list.add(parse());
            }
            position++; // skip ')'
            return factory.createList(list);
        }

        if (token.matches("-?\\d+")) {
            return factory.createNumber(Integer.parseInt(token));
        }

        return factory.createSymbol(token);
    }
}
