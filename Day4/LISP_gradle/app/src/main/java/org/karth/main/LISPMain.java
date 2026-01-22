package org.karth.main;

import java.util.List;
import java.util.Scanner;

import org.karth.evaluate.EvaluationVisitor;
import org.karth.factory.NodeFactory;
import org.karth.preprocess.Parser;
import org.karth.preprocess.Tokenizer;
import org.karth.visitor.Node;

public class LISPMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EvaluationVisitor evaluator = new EvaluationVisitor();

        System.out.println("LISP Interpreter (type exit to quit): ");

        while (true) {
            System.out.print(">> ");

            String input = sc.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting....");
                break;
            }

            try {

                List<String> tokens = Tokenizer.tokenize(input);

                NodeFactory factory = NodeFactory.getInstance();
                Parser parser = new Parser(tokens, factory);

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

        sc.close();

    }
}
