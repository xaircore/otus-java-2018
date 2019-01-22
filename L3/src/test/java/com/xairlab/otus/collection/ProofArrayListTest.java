package com.xairlab.otus.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProofArrayListTest {

    @Test
    void addAll() {

        List<Integer> list = new ArrayList<>();
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }

        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(list.size(), 10);

        Collections.addAll(list, array);
        assertEquals(list.size(), 110);

        int element = list.get(0);
        assertEquals(element, 1);

        element = list.get(11);
        assertEquals(element, 1);

        element = list.get(109);
        assertEquals(element, 99);
    }

    @Test
    void copy() {

        List<Integer> src = new ArrayList<>();
        List<Integer> dst = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            src.add(i);
            dst.add(i + 10);
        }

        Collections.copy(dst, src);
        assertEquals(src.size(), dst.size());

        int s1 = src.get(11);
        int d1 = dst.get(11);
        assertEquals(s1, d1);

        int s2 = src.get(11);
        int d2 = dst.get(11);
        assertEquals(s2, d2);

        int s3 = src.get(19);
        int d3 = dst.get(19);
        assertEquals(s3, d3);
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
        assertEquals(list.size(), 150);

        int e1 = list.get(0);
        assertEquals(e1, 0);

        int e2 = list.get(50);
        assertEquals(e2, 50);

        int e3 = list.get(149);
        assertEquals(e3, 199);
    }
}