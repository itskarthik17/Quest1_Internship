// package org.test;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;

// import org.evaluate.EvaluationVisitor;
// import org.factory.NodeFactory;
// import org.preProcess.Parser;
// import org.preProcess.Tokenizer;
// import org.visitor.Node;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class LispInterpreterTest {

// private NodeFactory factory;
// private EvaluationVisitor evaluator;

// @BeforeEach
// void setup() {
// factory = new NodeFactory();
// evaluator = new EvaluationVisitor();
// }

// // ---------------- Helper ----------------
// private Object eval(String input) {
// List<String> tokens = Tokenizer.tokenize(input);
// Parser parser = new Parser(tokens, factory);
// Node ast = parser.parse();

// if (parser.hasRemainingTokens()) {
// throw new RuntimeException("Unexpected token: " + parser.getCurrentToken());
// }

// return ast.accept(evaluator);
// }

// // ---------------- Arithmetic ----------------

// @Test
// void testAdditionIntegers() {
// assertEquals(3, eval("(+ 1 2)"));
// }

// @Test
// void testMultiplicationIntegers() {
// assertEquals(6, eval("(* 2 3)"));
// }

// @Test
// void testSubtraction() {
// assertEquals(2, eval("(- 5 3)"));
// }

// @Test
// void testDivisionFloating() {
// assertEquals(2.5, eval("(/ 5 2)"));
// }

// @Test
// void testMixedArithmetic() {
// assertEquals(3.5, eval("(+ 1 (/ 5 2))"));
// }

// // ---------------- Comparison ----------------

// @Test
// void testGreaterThanTrue() {
// assertEquals(1, eval("(> 5 3)"));
// }

// @Test
// void testGreaterThanFalse() {
// assertEquals(0, eval("(> 3 5)"));
// }

// @Test
// void testLessThanTrue() {
// assertEquals(1, eval("(< 2 5)"));
// }

// // ---------------- Conditional ----------------

// @Test
// void testIfTrueBranch() {
// assertEquals(10, eval("(if (> 5 3) 10 20)"));
// }

// @Test
// void testIfFalseBranch() {
// assertEquals(20, eval("(if (< 5 3) 10 20)"));
// }

// // ---------------- Variables ----------------

// @Test
// void testDefineVariable() {
// assertEquals(10, eval("(define x 10)"));
// }

// @Test
// void testVariableUsage() {
// eval("(define x 10)");
// assertEquals(15, eval("(+ x 5)"));
// }

// // ---------------- Nested Expressions ----------------

// @Test
// void testNestedExpression() {
// assertEquals(7, eval("(+ 1 (* 2 3))"));
// }

// // ---------------- Error Handling ----------------

// @Test
// void testDivisionByZero() {
// assertThrows(ArithmeticException.class, () -> eval("(/ 10 0)"));
// }

// @Test
// void testUndefinedVariable() {
// assertThrows(RuntimeException.class, () -> eval("(+ x 1)"));
// }

// @Test
// void testExtraClosingParenthesis() {
// assertThrows(RuntimeException.class, () -> eval("(+ 1 2))"));
// }

// @Test
// void testMissingClosingParenthesis() {
// assertThrows(RuntimeException.class, () -> eval("(+ 1 2"));
// }
// }
