package ru.nsu.anishchenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapsortTest {

    @Test
    void test0() {
        int[] test = {2, 3, 1};
        int[] actual = Heapsort.heapsort(test);
        int[] expected = {1, 2, 3};
        assertArrayEquals(actual, expected);
    }

    @Test
    void test1() {
        int[] test = {42};
        int[] actual = Heapsort.heapsort(test);
        int[] expected = {42};
        assertArrayEquals(actual, expected);
    }

    @Test
    void test2() {
        int[] test = {792, 721, 776, 319, 158};
        int[] actual = Heapsort.heapsort(test);
        int[] expected = {158, 319, 721, 776, 792};
        assertArrayEquals(actual, expected);
    }

    @Test
    void test3() {
        int[] test = {-26, 529, -510, 937};
        int[] actual = Heapsort.heapsort(test);
        int[] expected = {-510, -26, 529, 937};
        assertArrayEquals(actual, expected);
    }

    @Test
    void test4() {
        int[] test = {};
        int[] actual = Heapsort.heapsort(test);
        int[] expected = {};
        assertArrayEquals(actual, expected);
    }

    @Test
    void test5() {
        int[] test = {516, 750, 607, 845, -16, 907, 600, -712, 213, 694};
        int[] actual = Heapsort.heapsort(test);
        int[] expected = {-712, -16, 213, 516, 600, 607, 694, 750, 845, 907};
        assertArrayEquals(actual, expected);
    }
}