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
        return node.getValue(); // stored as double
    }

    @Override
    public Object visit(SymbolNode node) {
        return env.lookup(node.getName());
    }

    @Override
    public Object visit(ListNode node) {

        List<Node> elements = node.getElements();

        if (elements.isEmpty()) {
            throw new RuntimeException("Empty expression");
        }

        String operator = ((SymbolNode) elements.get(0)).getName();

        switch (operator) {

            case "+":
                return normalize(num(elements.get(1)) + num(elements.get(2)));

            case "-":
                return normalize(num(elements.get(1)) - num(elements.get(2)));

            case "*":
                return normalize(num(elements.get(1)) * num(elements.get(2)));

            case "/":
                double divisor = num(elements.get(2));
                if (divisor == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num(elements.get(1)) / divisor; // always floating

            case ">":
                return num(elements.get(1)) > num(elements.get(2)) ? "True" : "False";

            case "<":
                return num(elements.get(1)) < num(elements.get(2)) ? "True" : "False";

            case "define":
                if (!(elements.get(1) instanceof SymbolNode)) {
                    throw new RuntimeException("define requires a symbol");
                }
                String var = ((SymbolNode) elements.get(1)).getName();
                Object value = elements.get(2).accept(this);
                env.define(var, value);
                return value;

            case "if":
                double condition = num(elements.get(1));
                if (condition != 0) {
                    return elements.get(2).accept(this);
                } else {
                    return elements.get(3).accept(this);
                }

            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }

    // Convert evaluated value to double
    private double num(Node node) {
        Object value = node.accept(this);

        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Float) {
            return ((Float) value).doubleValue();
        }

        throw new RuntimeException("Expected numeric value but got: " + value);
    }

    private boolean isWhole(double value) {
        return value == Math.floor(value);
    }

    private Object normalize(double value) {
        if (isWhole(value)) {
            return (int) value;
        }
        return value;
    }
}
