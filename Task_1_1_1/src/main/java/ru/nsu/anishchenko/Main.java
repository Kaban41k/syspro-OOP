package ru.nsu.anishchenko;

import java.util.Arrays;

/**
 * Main class for example of heapsort work.
 */
public class Main {
    /**
     * Example of sorting array.
     *
     * @param args string of command arguments
     */
    public static void main(String[] args) {
        int[] example = {-234, 32, 1, 4, 20, -30};
        Heapsort.heapsort(example);

        System.out.println(Arrays.toString(example));
    }
}
