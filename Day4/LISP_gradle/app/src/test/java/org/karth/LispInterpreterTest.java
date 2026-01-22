package org.karth;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.karth.evaluate.EvaluationVisitor;
import org.karth.factory.NodeFactory;
import org.karth.preprocess.Parser;
import org.karth.preprocess.Tokenizer;
import org.karth.visitor.Node;

class LispInterpreterTest {

    private NodeFactory factory;
    private EvaluationVisitor evaluator;

    // ---------------- SETUP ----------------

    @BeforeEach
    void setup() {
        factory = NodeFactory.getInstance();
        evaluator = new EvaluationVisitor();
    }

    // ---------------- HELPER ----------------

    private Object eval(String input) {
        List<String> tokens = Tokenizer.tokenize(input);
        Parser parser = new Parser(tokens, factory);
        Node ast = parser.parse();

        if (parser.hasRemainingTokens()) {
            throw new RuntimeException(
                    "Unexpected token: " + parser.getCurrentToken());
        }
        return ast.accept(evaluator);
    }

    @Test
    void testDefineVariableInteger() {
        Assertions.assertEquals(10, eval("(define x 10)"), "integer variable defining testing");
    }

    @Test
    void testDefineVariableDouble() {
        Assertions.assertEquals(10.0, ((Double) eval("(define x 10.0)")), "integer variable defining testing");
    }

    @Test
    void testVariableUsage() {
        eval("(define x 10)");
        Assertions.assertEquals(15, eval("(+ x 5)"), "variable usage testing");
    }

    @Test
    void testNestedExpression() {
        Assertions.assertEquals(7, eval("(+ 1 (* 2 3))"), "Nested Expression testing");
    }

    @Test
    void testIfTrueBranch() {
        Assertions.assertEquals(10, eval("(if (> 5 3) 10 20)"), "if condition testing");
    }

    @Test
    void testIfFalseBranch() {
        Assertions.assertEquals(20, eval("(if (< 5 3) 10 20)"), "if condition testing");
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'(+ 1 2)', 3",
            "'(+ 1.4 2.6)', 4.0",
            "'(* 2 3)', 6",
            "'(- 5 3)', 2",
            "'(/ 5 2)', 2.5",
            "'(+ 1 (/ 5 2))', 3.5",
            "'(+ 1 (* 2 3))', 7"
    })
    void testArithmeticExpressions(String expression, double expected) {
        Object result = eval(expression);
        Assertions.assertEquals(
                expected,
                ((Number) result).doubleValue(),
                1e-9, "Arithmentic Expression Tesiting");
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'(> 5 3)', 1",
            "'(> 3 5)', 0",
            "'(< 2 5)', 1",
            "'(< 5 2)', 0"
    })
    void testRelationalExpressions(String expression, int expected) {
        Object result = eval(expression);
        Assertions.assertEquals(expected, result, "Parameterized Testing of Relational Expression");
    }
}
