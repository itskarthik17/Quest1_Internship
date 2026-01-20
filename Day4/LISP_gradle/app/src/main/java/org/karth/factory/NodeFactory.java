package org.karth.factory;

import java.util.List;

import org.karth.visitor.ListNode;
import org.karth.visitor.Node;
import org.karth.visitor.NumberNode;
import org.karth.visitor.SymbolNode;

public class NodeFactory {

    private static NodeFactory instance;

    private NodeFactory() {
    }

    public static NodeFactory getInstance() {
        if (instance == null) {
            instance = new NodeFactory();
        }
        return instance;
    }

    public NumberNode createNumber(double value) { // if the number is integer
        return new NumberNode(value, false);
    }

    public NumberNode createNumber(double value, boolean isFloatLiteral) { // this if number is boolean
        return new NumberNode(value, isFloatLiteral);
    }

    public SymbolNode createSymbol(String name) {
        return new SymbolNode(name);
    }

    public ListNode createList(List<Node> elements) {
        return new ListNode(elements);
    }
}
