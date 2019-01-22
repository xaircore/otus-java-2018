package com.xairlab.otus.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class MyArrayListTest {

    @Test
    void size() {

        List<Integer> list = new MyArrayList<>();
        assertEquals(list.size(), 0);

        list.add(1);
        assertEquals(list.size(), 1);

        list.remove(0);
        assertEquals(list.size(), 0);
    }

    @Test
    void isEmpty() {

        List<Integer> list = new MyArrayList<>();
        assertTrue(list.isEmpty());

        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void contains() {

        List<Integer> list = new MyArrayList<>();
        assertFalse(list.contains(0));

        list.add(0);
        assertTrue(list.contains(0));
        assertFalse(list.contains(5));
    }

    @Test
    void add() {

        List<Integer> list = new MyArrayList<>();
        assertTrue(list.add(1));
    }

    @Test
    void remove() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);

        int fst = list.remove(0);
        assertEquals(fst, 0);
    }

    @Test
    void containsAll() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(7);

        List<Integer> t = new ArrayList<>();
        t.add(0);
        t.add(7);
        assertTrue(list.containsAll(t));

        List<Integer> f = new ArrayList<>();
        f.add(0);
        f.add(9);
        assertFalse(list.containsAll(f));
    }

    @Test
    void addAll() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(7);

        List<Integer> list2 = new MyArrayList<>();
        list2.addAll(list);
        assertEquals(list2.size(), 2);

        int zero = list2.get(0);
        assertEquals(zero, 0);

        int fst = list2.get(1);
        assertEquals(fst, 7);
    }

    @Test
    void removeAll() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(9);
        list.removeAll(list2);
        assertEquals(list.size(), 2);

        int zero = list.get(0);
        assertEquals(zero, 0);

        int fst = list.get(1);
        assertEquals(fst, 7);
    }

    @Test
    void retainAll() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(9);
        list.retainAll(list2);
        assertEquals(list.size(), 2);

        int zero = list.get(0);
        assertEquals(zero, 5);

        int fst = list.get(1);
        assertEquals(fst, 9);
    }

    @Test
    void clear() {

        List<Integer> list = new MyArrayList<>();
        list.clear();
        assertEquals(list.size(), 0);

        list.add(0);
        list.add(7);
        list.clear();
        assertEquals(list.size(), 0);
    }

    @Test
    void get() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        int zero = list.get(0);
        assertEquals(zero, 0);

        int third = list.get(3);
        assertEquals(third, 9);
    }

    @Test
    void set() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        list.set(0, 10);
        list.set(3, 19);

        int zero = list.get(0);
        assertEquals(zero, 10);

        int third = list.get(3);
        assertEquals(third, 19);
    }

    @Test
    void indexOf() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(9);

        assertEquals(list.indexOf(5), 1);
        assertEquals(list.indexOf(20), -1);
    }

    @Test
    void lastIndexOf() {

        List<Integer> list = new MyArrayList<>();
        list.add(0);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(9);

        assertEquals(list.lastIndexOf(5), 2);
        assertEquals(list.lastIndexOf(20), -1);
    }
}