package com.xairlab.otus.solid;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM {

    static Logger logger = LoggerFactory.getLogger(ATM.class);

    private Map<Banknote, Box> inner;

    public ATM() {
        inner = new TreeMap<>();
        inner.put(Banknote.FIFTY, new Box(50));
        inner.put(Banknote.HUNDRED, new Box(100));
        inner.put(Banknote.TWO_HUNDRED, new Box(200));
        inner.put(Banknote.FIVE_HUNDRED, new Box(500));
        inner.put(Banknote.THOUSAND, new Box(1000));
        inner.put(Banknote.TWO_THOUSAND, new Box(2000));
        inner.put(Banknote.FIVE_THOUSAND, new Box(5000));
    }

    public int getTotal() {
        int sum = 0;
        for (Box box : inner.values()) {
            sum += box.getTotal();
        }
        logger.info("Остаток в банкомате " + sum);
        return sum;
    }

    public void put(Pair pair) {
        logger.info("Добавили в банкомат " + pair.getCount() + " купюр c номиналом " + pair.getBanknote());
        Box box = inner.get(pair.getBanknote());
        box.putBanknotes(pair.getCount());
    }

    private List<Box> getBoxes() {
        List<Box> boxes = new ArrayList<>();
        for (Box b : inner.values()) {
            boxes.add(b);
        }
        boxes.sort((Box a, Box b) -> b.getNominal() - a.getNominal());
        return boxes;
    }

    public List<Pair> get(int amount) {
        List<Pair> result = new ArrayList<>();
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
                Pair pair = new Pair(Banknote.getByNominal(b.getNominal()), banknotes);
                result.add(pair);
                currentAmount -= b.getNominal() * banknotes;
            }
        }
        logger.info("Сняли с банкомата " + amount);
        return result;
    }
}
