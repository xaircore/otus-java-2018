package com.xairlab.otus.patterns;

import com.xairlab.otus.patterns.atm.ATM;
import com.xairlab.otus.patterns.atm.Banknote;

public class Main {

    public static void main(String[] args) {
        Department department = new Department();

        ATM sbebank = new ATM(Banknote.FIFTY, Banknote.HUNDRED, Banknote.FIVE_HUNDRED, Banknote.THOUSAND);
        ATM alfa = new ATM(Banknote.HUNDRED, Banknote.TWO_HUNDRED, Banknote.HUNDRED, Banknote.TWO_THOUSAND);
        ATM vtb = new ATM(Banknote.THOUSAND, Banknote.TWO_THOUSAND, Banknote.FIVE_THOUSAND);

        department.addATM(sbebank);
        department.addATM(alfa);
        department.addATM(vtb);

        department.getDepartmentTotal();
    }
}