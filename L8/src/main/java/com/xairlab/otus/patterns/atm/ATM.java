package com.xairlab.otus.patterns.atm;

import java.util.*;

import com.xairlab.otus.patterns.api.DepartmentCommand;
import com.xairlab.otus.patterns.api.Maintance;
import com.xairlab.otus.patterns.api.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM implements State, Maintance {

    static Logger logger = LoggerFactory.getLogger(ATM.class);

    private Map<Banknote, Box> inner;
    private Map<Banknote, Box> state;
    private boolean maintance = false;

    public ATM(Banknote... banknotes) {
        inner = new TreeMap<>();
        for (Banknote banknote : banknotes) {
            inner.put(banknote, new Box(banknote));
        }
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
        if (maintance) {
            return;
        }
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
        if (maintance) {
            return result;
        }
        if (amount > getTotal()) {
            logger.error("Не смогли снять с банкомата " + amount + " т.к. привышен лимит средств хранящихся в нем");
            return result;
        }
        if (amount % 50 != 0) {
            logger.error("Попытались снять сумму не кратную 50");
            return result;
        }
        int currentAmount = amount;
        for (Box b : getBoxes()) {
            if (currentAmount == 0) {
                break;
            }
            if (b.getCount() > 0) {
                int banknotes = currentAmount / b.getNominal();
                if (banknotes > b.getCount()) {
                    banknotes = b.getCount();
                }
                b.getBanknotes(banknotes);
                for (int i = 0; i < banknotes; i++) {
                    result.add(b.getBanknoteType());
                }
                currentAmount -= b.getNominal() * banknotes;
            }
        }
        logger.info("Сняли с банкомата " + amount);
        return result;
    }

    public void executeDepartmentCommand(DepartmentCommand command) {
        command.execute(this);
    }

    @Override
    public void saveState() {
        Map<Banknote, Box> copy = new TreeMap<>();
        for (Map.Entry entry : inner.entrySet()) {
            Box copyBox = (Box) entry.getValue();
            copy.put((Banknote) entry.getKey(), copyBox.copy());
        }
        state = copy;
    }

    @Override
    public void restoreState() {
        inner = state;
    }

    @Override
    public void on() {
        maintance = true;
    }

    @Override
    public void off() {
        maintance = false;
    }
}
