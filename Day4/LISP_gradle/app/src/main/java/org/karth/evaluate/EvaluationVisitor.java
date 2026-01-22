package org.karth.evaluate;

import java.util.List;

import org.karth.singleton.GlobalEnvironment;
import org.karth.visitor.ListNode;
import org.karth.visitor.Node;
import org.karth.visitor.NumberNode;
import org.karth.visitor.SymbolNode;
import org.karth.visitor.Visitor;

public class EvaluationVisitor implements Visitor {

    private final GlobalEnvironment env = GlobalEnvironment.getInstance();

    @Override
    public Object visit(NumberNode node) {
        double value = node.getValue();
        if (node.isFloatLiteral()) {
            return value; // keep as double for float literals
        }
        return normalize(value); // convert whole numbers to int
    }

    @Override
    public Object visit(SymbolNode node) {
        String name = node.getName();
        if ("true".equalsIgnoreCase(name)) {
            return 1;
        }
        if ("false".equalsIgnoreCase(name)) {
            return 0;
        }

        return env.lookup(name);
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
                return num(elements.get(1)) > num(elements.get(2)) ? 1 : 0;

            case "<":
                return num(elements.get(1)) < num(elements.get(2)) ? 1 : 0;

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

                Object result;
                if (condition != 0) {
                    result = elements.get(2).accept(this);
                } else {
                    result = elements.get(3).accept(this);
                }

                if (result instanceof Number) {
                    double v = ((Number) result).doubleValue();
                    return normalize(v);
                }

                return result;

            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }

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

        if (value instanceof Boolean) {
            return (Boolean) value ? 1 : 0;
        }

        if (value instanceof String) {
            if ("true".equalsIgnoreCase(((String) value))) {
                return 1.0;
            }
            if ("false".equalsIgnoreCase(((String) value))) {
                return 0.0;
            }
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
