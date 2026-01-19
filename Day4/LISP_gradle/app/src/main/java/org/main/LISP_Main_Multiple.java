package org.main;

import java.util.List;
import java.util.Scanner;

import org.evaluate.EvaluationVisitor;
import org.factory.NodeFactory;
import org.preProcess.Parser;
import org.preProcess.Tokenizer;
import org.visitor.Node;

public class LISP_Main_Multiple {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Expression: ");
        String input = sc.nextLine();

        try {
            List<String> tokens = Tokenizer.tokenize(input);

            NodeFactory factory = new NodeFactory();
            Parser parser = new Parser(tokens, factory);
            EvaluationVisitor evaluator = new EvaluationVisitor();

            Object result = null;

            while (parser.hasRemainingTokens()) {
                Node ast = parser.parse();
                result = ast.accept(evaluator);
            }

            if (result != null) {
                System.out.println("Result: " + result);
            }

        } catch (RuntimeException e) {
            System.err.println("LISP Error: " + e.getMessage());
        }
    }
}
