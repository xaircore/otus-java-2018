package com.xairlab.otus.collection;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProofArrayListTest {

    @Test
    void addAll() {

        List<Integer> list = new ArrayList<>();
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }

        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertTrue(list.size() == 10);

        Collections.addAll(list, array);
        assertTrue(list.size() == 110);
        assertTrue(list.get(0) == 1);
        assertTrue(list.get(11) == 1);
        assertTrue(list.get(109) == 99);
    }

    @Test
    void copy() {

        List<Integer> src = new ArrayList<>();
        List<Integer> dst = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            src.add(i);
            dst.add(i + 10);
        }

        Collections.copy(dst, src);
        assertTrue(src.size() == dst.size());
        assertTrue(src.get(0) == dst.get(0));
        assertTrue(src.get(11) == dst.get(11));
        assertTrue(src.get(55) == dst.get(55));
        assertTrue(src.get(99) == dst.get(99));
    }

    @Test
    void sort() {

        List<Integer> list = new ArrayList<>();
        Integer[] array = new Integer[50];
        Integer[] array2 = new Integer[50];
        Integer[] array3 = new Integer[50];

        for (int i = 50; i < 100; i++) {
            array[i - 50] = i;
        }
        for (int i = 0; i < 50; i++) {
            array2[i] = i;
        }
        for (int i = 150; i < 200; i++) {
            array3[i - 150] = i;
        }

        Collections.addAll(list, array);
        Collections.addAll(list, array2);
        Collections.addAll(list, array3);
        Collections.sort(list);
        assertTrue(list.size() == 150);
        assertTrue(list.get(0) == 0);
        assertTrue(list.get(50) == 50);
        assertTrue(list.get(149) == 199);
    }
}