package com.xairlab.otus.solid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BanknoteTest {

    @Test
    void getByNominal() {
        assertEquals(Banknote.getByNominal(50), Banknote.FIFTY);
        assertEquals(Banknote.getByNominal(100), Banknote.HUNDRED);
        assertEquals(Banknote.getByNominal(200), Banknote.TWO_HUNDRED);
        assertEquals(Banknote.getByNominal(500), Banknote.FIVE_HUNDRED);
        assertEquals(Banknote.getByNominal(1000), Banknote.THOUSAND);
        assertEquals(Banknote.getByNominal(2000), Banknote.TWO_THOUSAND);
        assertEquals(Banknote.getByNominal(5000), Banknote.FIVE_THOUSAND);
    }

    @Test()
    void getByNominalThrow() {
        assertThrows(IllegalStateException.class, () -> {
            Banknote.getByNominal(10000);
        });
    }

    @Test
    void getByBanknote() {
        assertEquals(Banknote.getByBanknote(Banknote.FIFTY), 50);
        assertEquals(Banknote.getByBanknote(Banknote.HUNDRED), 100);
        assertEquals(Banknote.getByBanknote(Banknote.TWO_HUNDRED), 200);
        assertEquals(Banknote.getByBanknote(Banknote.FIVE_HUNDRED), 500);
        assertEquals(Banknote.getByBanknote(Banknote.THOUSAND), 1000);
        assertEquals(Banknote.getByBanknote(Banknote.TWO_THOUSAND), 2000);
        assertEquals(Banknote.getByBanknote(Banknote.FIVE_THOUSAND), 5000);
    }
}