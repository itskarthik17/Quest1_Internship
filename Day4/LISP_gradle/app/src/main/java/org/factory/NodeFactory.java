package org.factory;

import java.util.List;

import org.visitor.ListNode;
import org.visitor.Node;
import org.visitor.NumberNode;
import org.visitor.SymbolNode;

public class NodeFactory {

    public NumberNode createNumber(double value) {
        return new NumberNode(value);
    }

    public SymbolNode createSymbol(String name) {
        return new SymbolNode(name);
    }

    public ListNode createList(List<Node> elements) {
        return new ListNode(elements);
    }
}
