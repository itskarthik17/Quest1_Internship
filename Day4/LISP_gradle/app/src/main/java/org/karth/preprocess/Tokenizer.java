package org.karth.preprocess;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    public static List<String> tokenize(String input) {
        String input1 = input.replace("(", " ( ").replace(")", " ) ");
        return Arrays.asList(input1.trim().split("\\s+"));
    }

}
