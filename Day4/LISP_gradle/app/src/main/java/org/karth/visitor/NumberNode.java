package org.karth.visitor;

public class NumberNode implements Node {

    private final double value;
    private final boolean isFloatLiteral;

    public NumberNode(double value, boolean isFloatLiteral) {
        this.value = value;
        this.isFloatLiteral = isFloatLiteral;
    }

    public double getValue() {
        return value;
    }

    public boolean isFloatLiteral() {
        return isFloatLiteral;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
