package org.singleton;

import java.util.HashMap;
import java.util.Map;

public class GlobalEnvironment {
    private static GlobalEnvironment instance;
    private final Map<String, Object> variables = new HashMap<>();

    private GlobalEnvironment() {
    }

    public static GlobalEnvironment getInstance() {
        if (instance == null) {
            instance = new GlobalEnvironment();
        }
        return instance;
    }

    public void define(String name, Object value) {
        variables.put(name, value);
    }

    public Object lookup(String name) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Undefined symbol: " + name);
        }
        return variables.get(name);
    }
}
