package com.xairlab.otus.patterns;

import com.xairlab.otus.patterns.atm.ATM;
import com.xairlab.otus.patterns.atm.Banknote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetCashTest {

    @Test
    void getCash(){
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

        assertEquals(department.getDepartmentTotal(), 21500);
    }
}
