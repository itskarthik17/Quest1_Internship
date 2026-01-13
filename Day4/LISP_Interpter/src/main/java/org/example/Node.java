package org.example;

public interface Node {
    <T> T accept(Visitor<T> visitor);
}
