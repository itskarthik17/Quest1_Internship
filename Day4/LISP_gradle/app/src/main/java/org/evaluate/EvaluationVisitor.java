package org.evaluate;

import java.util.List;

import org.singleton.GlobalEnvironment;
import org.visitor.ListNode;
import org.visitor.Node;
import org.visitor.NumberNode;
import org.visitor.SymbolNode;
import org.visitor.Visitor;

public class EvaluationVisitor implements Visitor {

    private final GlobalEnvironment env = GlobalEnvironment.getInstance();

    @Override
    public Object visit(NumberNode node) {
        return node.getValue();
    }

    @Override
    public Object visit(SymbolNode node) {
        return env.lookup(node.getName());
    }

    @Override
    public Object visit(ListNode node) {
        List<Node> elements = node.getElements();
        String operator = ((SymbolNode) elements.get(0)).getName();

        switch (operator) {
            case "+":
                return (int) elements.get(1).accept(this)
                        + (int) elements.get(2).accept(this);

            case "*":
                return (int) elements.get(1).accept(this)
                        * (int) elements.get(2).accept(this);

            case ">":
                if ((int) elements.get(1).accept(this) > (int) elements.get(2).accept(this)) {
                    return 1;
                }
                return 0;

            case "define":
                String var = ((SymbolNode) elements.get(1)).getName();
                Object value = elements.get(2).accept(this);
                env.define(var, value);
                return value;

            case "if":
                int condition = (int) elements.get(1).accept(this);
                if (condition != 0) {
                    return elements.get(2).accept(this);
                } else {
                    return elements.get(3).accept(this);
                }

            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}
