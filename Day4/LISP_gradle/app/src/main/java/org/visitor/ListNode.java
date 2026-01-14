package org.visitor;

import java.util.List;

public class ListNode implements Node {

    public List<Node> elements;

    public ListNode(List<Node> elements) {
        this.elements = elements;
    }

    public List<Node> getElements() {
        return elements;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
