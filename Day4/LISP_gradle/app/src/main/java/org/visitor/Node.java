package org.visitor;

public interface Node {
    public Object accept(Visitor visitor);
}
