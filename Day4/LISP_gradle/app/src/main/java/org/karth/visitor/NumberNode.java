package org.karth.visitor;

public class NumberNode implements Node {

    private final double value;
    private final boolean isFloat;

    public NumberNode(double value, boolean isFloatLiteral) {
        this.value = value;
        this.isFloat = isFloatLiteral;
    }

    public double getValue() {
        return value;
    }

    public boolean isFloatLiteral() {
        return isFloat;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
