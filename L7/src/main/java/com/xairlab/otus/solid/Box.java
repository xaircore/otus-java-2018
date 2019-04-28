package com.xairlab.otus.solid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Box {

    private static Logger logger = LoggerFactory.getLogger(Box.class);

    private final Banknote nominal;
    private int count = 0;

    public Box(Banknote nominal) {
        this.nominal = nominal;
        logger.info("Устанавливаем кассету номиналом " + getNominal() + " в банкомат");
    }

    public void putBanknotes(int banknotesCount) {
        logger.info("Добавили " + banknotesCount + " купюр в кассету с номиналом " + getNominal());
        count += banknotesCount;
    }

    public void getBanknotes(int banknotesCount) {
        if (count >= banknotesCount) {
            logger.info("Отдаем " + banknotesCount + " купюр из кассеты с номиналом " + getNominal());
            count -= banknotesCount;
        }
    }

    public int getNominal() {
        return Banknote.getByBanknote(nominal);
    }

    public Banknote getBanknoteType() {
        return nominal;
    }

    public int getTotal() {
        return count * getNominal();
    }

    public int getCount() {
        return count;
    }
}
