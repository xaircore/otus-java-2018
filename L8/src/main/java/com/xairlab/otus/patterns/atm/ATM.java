package com.xairlab.otus.patterns.atm;

import java.util.*;

import com.xairlab.otus.patterns.api.DepartmentCommand;
import com.xairlab.otus.patterns.api.Maintance;
import com.xairlab.otus.patterns.api.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM implements State, Maintance {

    private static Logger logger = LoggerFactory.getLogger(ATM.class);

    private Map<Banknote, Box> currentState;
    private Map<Banknote, Box> restoreState;
    private boolean maintenance = false;

    public ATM(Banknote... banknotes) {
        currentState = new TreeMap<>();
        for (Banknote banknote : banknotes) {
            currentState.put(banknote, new Box(banknote));
        }
    }

    public int getTotal() {
        int sum = 0;
        for (Box box : currentState.values()) {
            sum += box.getTotal();
        }
        logger.info("Остаток в банкомате " + sum);
        return sum;
    }

    public void put(Banknote banknote, int count) {
        if (maintenance) {
            return;
        }
        logger.info("Добавили в банкомат " + count + " купюр c номиналом " + banknote.getNominal());
        Box box = currentState.get(banknote);
        box.putBanknotes(count);
    }

    private List<Box> getBoxes() {
        List<Box> boxes = new ArrayList<>();
        for (Box b : currentState.values()) {
            boxes.add(b);
        }
        boxes.sort((Box a, Box b) -> b.getNominal() - a.getNominal());
        return boxes;
    }

    public List<Banknote> get(int amount) {
        List<Banknote> result = new ArrayList<>();
        if (maintenance) {
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
                    result.add(b.getBanknote());
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

    private Map<Banknote, Box> getStateCopy(Map<Banknote, Box> state) {
        Map<Banknote, Box> copy = new TreeMap<>();
        for (Map.Entry entry : state.entrySet()) {
            Box copyBox = (Box) entry.getValue();
            copy.put((Banknote) entry.getKey(), copyBox.copy());
        }
        return copy;
    }

    @Override
    public void saveState() {
        restoreState = getStateCopy(currentState);
    }

    @Override
    public void restoreState() {
        currentState = getStateCopy(restoreState);
    }

    @Override
    public void on() {
        maintenance = true;
    }

    @Override
    public void off() {
        maintenance = false;
    }
}
