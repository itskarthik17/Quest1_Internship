package org.main;

import java.util.List;
import java.util.Scanner;

import org.evaluate.EvaluationVisitor;
import org.factory.NodeFactory;
import org.preprocess.Tokenizer;
import org.visitor.Node;
import org.preprocess.Parser;

public class LISP_Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EvaluationVisitor evaluator = new EvaluationVisitor();

        System.out.println("LISP Interpreter (type exit to quit): ");

        while (true) {
            System.out.print(">> ");

            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
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
                System.out.println("LISP Error: " + e.getMessage());
            }
        }

        sc.close();

    }
}
