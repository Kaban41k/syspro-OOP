package ru.nsu.anishchenko;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] example = {-234, 32, 1, 4, 20, -30};
        Heapsort.heapsort(example);

        System.out.println(Arrays.toString(example));
    }
}
