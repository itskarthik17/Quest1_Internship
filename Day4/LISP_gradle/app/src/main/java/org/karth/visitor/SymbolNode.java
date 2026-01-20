package org.karth.visitor;

public class SymbolNode implements Node {

    public String name;

    public SymbolNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
