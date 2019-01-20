package com.xairlab.otus.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayListTest {

    @Test
    void size() {

        List<Integer> list = new ArrayList<>();
        assertTrue(list.size() == 0);

        list.add(1);
        assertTrue(list.size() == 1);

        list.remove(0);
        assertTrue(list.size() == 0);
    }

    @Test
    void isEmpty() {

        List<Integer> list = new ArrayList<>();
        assertTrue(list.isEmpty());

        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void contains() {

        List<Integer> list = new ArrayList<>();
        assertFalse(list.contains(0));

        list.add(0);
        assertTrue(list.contains(0));
        assertFalse(list.contains(5));
    }

    @Test
    void add() {

        List<Integer> list = new ArrayList<>();
        assertTrue(list.add(1));
    }

    @Test
    void remove() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        assertTrue(list.remove(0) == 0);
    }

    @Test
    void containsAll() {

        List<Integer> list = new ArrayList<>();
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

        List<Integer> list2= new ArrayList<>();
        list2.addAll(list);
        assertTrue(list2.size() == 2);
        assertTrue(list2.get(0) == 0);
        assertTrue(list2.get(1) == 7);
    }

    @Test
    void removeAll() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        List<Integer> list2= new ArrayList<>();
        list2.add(5);
        list2.add(9);
        list.removeAll(list2);
        assertTrue(list.size() == 2);
        assertTrue(list.get(0) == 0);
        assertTrue(list.get(1) == 7);
    }

    @Test
    void retainAll() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        List<Integer> list2= new ArrayList<>();
        list2.add(5);
        list2.add(9);
        list.retainAll(list2);
        assertTrue(list.size() == 2);
        assertTrue(list.get(0) == 5);
        assertTrue(list.get(1) == 9);
    }

    @Test
    void clear() {

        List<Integer> list = new ArrayList<>();
        list.clear();
        assertTrue(list.size() == 0);

        list.add(0);
        list.add(7);
        list.clear();
        assertTrue(list.size() == 0);
    }

    @Test
    void get() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        assertTrue(list.get(0) == 0);
        assertTrue(list.get(3) == 9);
    }

    @Test
    void set() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(7);
        list.add(9);

        list.set(0, 10);
        list.set(3, 19);
        assertTrue(list.get(0) == 10);
        assertTrue(list.get(3) == 19);
    }

    @Test
    void indexOf() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(9);

        assertTrue(list.indexOf(5) == 1);
        assertTrue(list.indexOf(20) == -1);
    }

    @Test
    void lastIndexOf() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(9);

        assertTrue(list.lastIndexOf(5) == 2);
        assertTrue(list.indexOf(20) == -1);
    }
}