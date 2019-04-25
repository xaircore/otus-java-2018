package com.xairlab.otus.patterns.commands;

import com.xairlab.otus.patterns.api.DepartmentCommand;
import com.xairlab.otus.patterns.atm.ATM;

public class GetCash implements DepartmentCommand {

    private int sum = 0;

    @Override
    public void execute(ATM atm) {
        sum = atm.getTotal();
    }

    public int getSum() {
        return sum;
    }
}
