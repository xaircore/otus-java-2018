package com.xairlab.otus.patterns.commands;

import com.xairlab.otus.patterns.api.DepartmentCommand;
import com.xairlab.otus.patterns.atm.ATM;

public class MaintanceOn implements DepartmentCommand {
    @Override
    public void execute(ATM atm) {
        atm.on();
    }
}
