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

        atm.put(Banknote.FIFTY, 2);
        atm.put(Banknote.TWO_HUNDRED, 1);
        atm.put(Banknote.FIVE_THOUSAND, 3);
        assertEquals(15300, atm.getTotal());

        atm.get(15000);
        assertEquals(300, atm.getTotal());
    }

    @Test
    void get() {
        ATM atm = new ATM();
        atm.put(Banknote.FIFTY, 2);
        atm.put(Banknote.TWO_HUNDRED, 1);

        List<Banknote> pairs = atm.get(400);
        assertEquals(pairs.size(), 0);

        List<Banknote> expected = new ArrayList<>();
        expected.add(Banknote.TWO_HUNDRED);
        expected.add(Banknote.FIFTY);
        expected.add(Banknote.FIFTY);

        List<Banknote> pairs2 = atm.get(300);
        assertEquals(pairs2.size(), expected.size());
        assertEquals(pairs2.get(0), expected.get(0));
        assertEquals(pairs2.get(1), expected.get(1));
        assertEquals(pairs2.get(2), expected.get(2));
    }

    @Test
    void get_20() {
        ATM atm = new ATM();
        atm.put(Banknote.FIFTY, 2);
        atm.put(Banknote.TWO_HUNDRED, 1);

        List<Banknote> pairs = atm.get(20);
        assertEquals(pairs.size(), 0);
    }

    @Test
    void get_1_000_000() {
        ATM atm = new ATM();
        atm.put(Banknote.FIFTY, 2);
        atm.put(Banknote.TWO_HUNDRED, 1);

        List<Banknote> pairs = atm.get(1_000_000);
        assertEquals(pairs.size(), 0);
    }
}