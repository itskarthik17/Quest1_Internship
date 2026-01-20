package org.karth.preprocess;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    public static List<String> tokenize(String input) {
        input = input.replace("(", " ( ").replace(")", " ) ");
        return Arrays.asList(input.trim().split("\\s+"));
    }

}
