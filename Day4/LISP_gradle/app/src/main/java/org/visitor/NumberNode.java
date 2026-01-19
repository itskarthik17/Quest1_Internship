package org.visitor;

public class NumberNode implements Node {

    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
