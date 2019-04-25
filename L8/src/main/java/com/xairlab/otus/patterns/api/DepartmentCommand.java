package com.xairlab.otus.patterns.api;

import com.xairlab.otus.patterns.atm.ATM;

public interface DepartmentCommand {

    void execute(ATM atm);
}
