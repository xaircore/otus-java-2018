package com.xairlab.otus.solid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void nominal() {

        Box b = new Box(10);
        assertEquals(10, b.getNominal());
    }

    @Test
    void total() {

        Box b = new Box(50);
        assertEquals(0, b.getTotal());

        b.putBanknotes(2);
        assertEquals(100, b.getTotal());

        b.getBanknotes(1);
        assertEquals(50, b.getTotal());

        b.getBanknotes(6);
        assertEquals(50, b.getTotal());
    }

    @Test
    void count() {
        Box b = new Box(10);
        assertEquals(0, b.getCount());

        b.putBanknotes(2);
        assertEquals(2, b.getCount());

        b.getBanknotes(1);
        assertEquals(1, b.getCount());
    }
}