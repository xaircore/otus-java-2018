package com.xairlab.otus.solid;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM {

    private static Logger logger = LoggerFactory.getLogger(ATM.class);

    private Map<Banknote, Box> inner;

    public ATM() {
        inner = new TreeMap<>();
        inner.put(Banknote.FIFTY, new Box(Banknote.FIFTY));
        inner.put(Banknote.HUNDRED, new Box(Banknote.HUNDRED));
        inner.put(Banknote.TWO_HUNDRED, new Box(Banknote.TWO_HUNDRED));
        inner.put(Banknote.FIVE_HUNDRED, new Box(Banknote.FIVE_HUNDRED));
        inner.put(Banknote.THOUSAND, new Box(Banknote.THOUSAND));
        inner.put(Banknote.TWO_THOUSAND, new Box(Banknote.TWO_THOUSAND));
        inner.put(Banknote.FIVE_THOUSAND, new Box(Banknote.FIVE_THOUSAND));
    }

    public int getTotal() {
        int sum = 0;
        for (Box box : inner.values()) {
            sum += box.getTotal();
        }
        logger.info("Остаток в банкомате " + sum);
        return sum;
    }

    public void put(Banknote banknote, int count) {
        logger.info("Добавили в банкомат " + count + " купюр c номиналом " + Banknote.getByBanknote(banknote));
        Box box = inner.get(banknote);
        box.putBanknotes(count);
    }

    private List<Box> getBoxes() {
        List<Box> boxes = new ArrayList<>();
        for (Box b : inner.values()) {
            boxes.add(b);
        }
        boxes.sort((Box a, Box b) -> b.getNominal() - a.getNominal());
        return boxes;
    }

    public List<Banknote> get(int amount) {
        List<Banknote> result = new ArrayList<>();
        if (amount > getTotal()) {
            logger.error("Не смогли снять с банкомата " + amount + " т.к. привышен лимит средств хранящихся в нем");
            return result;
        }
        if (amount % 50 != 0){
            logger.error("Попытались снять сумму не кратную 50");
            return result;
        }
        int currentAmount = amount;
        for (Box b : getBoxes()) {
            if (b.getCount() > 0) {
                int banknotes = currentAmount / b.getNominal();
                b.getBanknotes(banknotes);
                for (int i = 0; i< banknotes; i++){
                    result.add(b.getBanknoteType());
                }
                currentAmount -= b.getNominal() * banknotes;
            }
        }
        logger.info("Сняли с банкомата " + amount);
        return result;
    }
}
