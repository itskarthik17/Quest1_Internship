package org.example;

import java.util.List;

public class EvaluationVisitor implements Visitor<Integer> {

    @Override
    public Integer visitNumber(NumberNode numberNode) {
        return numberNode.getValue();
    }

    @Override
    public Integer visitSymbol(SymbolNode symbolNode) {
        throw new RuntimeException("Undefined symbol: " + symbolNode.getName());
    }

    @Override
    public Integer visitList(ListNode listNode) {

        List<Node> elements = listNode.getElements();
        SymbolNode operator = (SymbolNode) elements.get(0);
        String op = operator.getName();

        int result;

        switch (op) {
            case "+":
                result = 0;
                for (int i = 1; i < elements.size(); i++) {
                    result += elements.get(i).accept(this);
                }
                return result;

            case "*":
                result = 1;
                for (int i = 1; i < elements.size(); i++) {
                    result *= elements.get(i).accept(this);
                }
                return result;

            default:
                throw new RuntimeException("Unsupported operator: " + op);
        }
    }
}
