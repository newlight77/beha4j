package io.github.newlight77;

@FunctionalInterface
public interface Printer {
    static void printConsole(String text) {
        System.out.println(text);
    }

    void print(String name);
}
