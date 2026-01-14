package org.visitor;

public interface Visitor {
    public Object visit(NumberNode node);

    public Object visit(SymbolNode node);

    public Object visit(ListNode node);
}