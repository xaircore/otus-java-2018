package com.xairlab.otus.solid;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    @Test
    void total() {

        ATM atm = new ATM();
        assertEquals(0, atm.getTotal());

        atm.put(new Pair(Banknote.FIFTY, 2));
        atm.put(new Pair(Banknote.TWO_HUNDRED, 1));
        atm.put(new Pair(Banknote.FIVE_THOUSAND, 3));
        assertEquals(15300, atm.getTotal());

        atm.get(15000);
        assertEquals(300, atm.getTotal());
    }

    @Test
    void get() {
        ATM atm = new ATM();
        atm.put(new Pair(Banknote.FIFTY, 2));
        atm.put(new Pair(Banknote.TWO_HUNDRED, 1));

        List<Pair> pairs = atm.get(400);
        assertEquals(pairs.size(), 0);

        List<Pair> expected = new ArrayList<>();
        expected.add(new Pair(Banknote.TWO_HUNDRED, 1));
        expected.add(new Pair(Banknote.FIFTY, 2));

        List<Pair> pairs2 = atm.get(300);
        assertEquals(pairs2.size(), expected.size());
        assertEquals(pairs2.get(0).getBanknote(), expected.get(0).getBanknote());
        assertEquals(pairs2.get(0).getCount(), expected.get(0).getCount());
        assertEquals(pairs2.get(1).getBanknote(), expected.get(1).getBanknote());
        assertEquals(pairs2.get(1).getCount(), expected.get(1).getCount());
    }

    @Test
    void get_20() {
        ATM atm = new ATM();
        atm.put(new Pair(Banknote.FIFTY, 2));
        atm.put(new Pair(Banknote.TWO_HUNDRED, 1));

        List<Pair> pairs = atm.get(20);
        assertEquals(pairs.size(), 0);
    }

    @Test
    void get_1_000_000() {
        ATM atm = new ATM();
        atm.put(new Pair(Banknote.FIFTY, 2));
        atm.put(new Pair(Banknote.TWO_HUNDRED, 1));

        List<Pair> pairs = atm.get(1_000_000);
        assertEquals(pairs.size(), 0);
    }
}