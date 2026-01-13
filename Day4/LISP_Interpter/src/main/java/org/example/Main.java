package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter LISP Expression: ");
        String input = sc.nextLine();

        List<String> tokens = Tokenizer.tokenize(input);
        System.out.println(tokens);

        Parser parser = new Parser(tokens);
        Node ast = parser.parse();

        EvaluationVisitor evaluator = new EvaluationVisitor();
        int result = ast.accept(evaluator);

        System.out.println("Result = " + result);
        
    }
}