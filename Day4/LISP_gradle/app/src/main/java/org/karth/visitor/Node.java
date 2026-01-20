package org.karth.visitor;

public interface Node {
    public Object accept(Visitor visitor);
}
