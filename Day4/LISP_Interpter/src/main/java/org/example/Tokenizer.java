package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public static List<String> tokenize(String input) {

        input = input.replace("(", " ( ").replace(")", " ) ");

        String[] raw = input.trim().split("\\s+");
        List<String> tokens = new ArrayList<>();

        for (String token : raw) {
            tokens.add(token);
        }
        return tokens;
    }
}
