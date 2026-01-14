package org.visitor;

public class NumberNode implements Node {

    public int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
