package com.xairlab.otus.solid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Box {

    static Logger logger = LoggerFactory.getLogger(Box.class);

    private final int nominal;
    private int count = 0;

    public Box(int nominal) {
        logger.info("Устанавливаем кассету номиналом " + nominal + " в банкомат");
        this.nominal = nominal;
    }

    public void putBanknotes(int banknotesCount) {
        logger.info("Добавили " + banknotesCount + " купюр в кассету с номиналом " + nominal);
        count += banknotesCount;
    }

    public void getBanknotes(int banknotesCount) {
        if (count >= banknotesCount) {
            logger.info("Отдаем " + banknotesCount + " купюр из кассеты с номиналом " + nominal);
            count -= banknotesCount;
        }
    }

    public int getNominal() {
        return nominal;
    }

    public int getTotal() {
        return count * nominal;
    }

    public int getCount() {
        return count;
    }
}
