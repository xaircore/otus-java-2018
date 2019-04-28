package com.xairlab.otus.patterns.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Box {

    private static Logger logger = LoggerFactory.getLogger(Box.class);

    private final Banknote banknote;
    private int count = 0;

    public Box(Banknote banknote) {
        this.banknote = banknote;
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
        return banknote.getNominal();
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getTotal() {
        return count * getNominal();
    }

    public int getCount() {
        return count;
    }

    public Box copy() {
        Box b = new Box(getBanknote());
        b.putBanknotes(getCount());
        return b;
    }
}
