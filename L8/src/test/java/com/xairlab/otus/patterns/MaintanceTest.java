package com.xairlab.otus.patterns;

import com.xairlab.otus.patterns.atm.ATM;
import com.xairlab.otus.patterns.atm.Banknote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaintanceTest {

    @Test
    void maintance() {
        Department department = new Department();

        ATM sbebank = new ATM(Banknote.FIFTY, Banknote.HUNDRED, Banknote.FIVE_HUNDRED, Banknote.THOUSAND);
        sbebank.put(Banknote.FIFTY, 10);

        ATM alfa = new ATM(Banknote.HUNDRED, Banknote.TWO_HUNDRED, Banknote.HUNDRED, Banknote.TWO_THOUSAND);
        alfa.put(Banknote.TWO_HUNDRED, 1);
        alfa.put(Banknote.HUNDRED, 8);

        ATM vtb = new ATM(Banknote.THOUSAND, Banknote.TWO_THOUSAND, Banknote.FIVE_THOUSAND);
        vtb.put(Banknote.FIVE_THOUSAND, 4);

        department.addATM(sbebank);
        department.addATM(alfa);
        department.addATM(vtb);

        department.maintanceOn();
        sbebank.get(200);
        alfa.get(1000);
        assertEquals(21500, department.getDepartmentTotal());

        department.maintanceOff();
        sbebank.get(200);
        alfa.get(1000);
        assertEquals(20300, department.getDepartmentTotal());
    }
}
