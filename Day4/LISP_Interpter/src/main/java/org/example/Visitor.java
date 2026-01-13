package org.example;

public interface Visitor<T> {
    T visitNumber(NumberNode numberNode);
    T visitSymbol(SymbolNode symbolNode);
    T visitList(ListNode listNode);
}

