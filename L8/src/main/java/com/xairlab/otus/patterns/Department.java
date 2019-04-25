package com.xairlab.otus.patterns;

import com.xairlab.otus.patterns.atm.ATM;
import com.xairlab.otus.patterns.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Department {

    static Logger logger = LoggerFactory.getLogger(Department.class);

    private List<ATM> atms;
    private int sum;

    public Department() {
        atms = new ArrayList<>();
        sum = 0;
    }

    public void addATM(ATM atm) {
        logger.info("Добавили банкомат в департамент");
        atms.add(atm);
    }

    public int getDepartmentTotal() {
        getTotalATM();
        logger.info("Собрали деньги с банкматов");
        int result = sum;
        sum = 0;
        return result;
    }

    public void saveState(){
        logger.info("Сохранили состояние банкоматов");
        for (ATM atm : atms) {
            atm.executeDepartmentCommand(new SaveState());
        }
    }

    public void restoreState(){
        logger.info("Восстановили состояние банкоматов");
        for (ATM atm : atms) {
            atm.executeDepartmentCommand(new RestoreState());
        }
    }

    public void maintanceOn(){
        logger.info("Отправили банкоматы на тенхническле обслуживание");
        for (ATM atm : atms) {
            atm.executeDepartmentCommand(new MaintanceOn());
        }
    }

    public void maintanceOff(){
        logger.info("Вернули банкоматы с технического обслуживания");
        for (ATM atm : atms) {
            atm.executeDepartmentCommand(new MaintanceOff());
        }
    }

    private void getTotalATM() {
        for (ATM atm : atms) {
            GetCash command = new GetCash();
            atm.executeDepartmentCommand(command);
            sum += command.getSum();
        }
    }
}
