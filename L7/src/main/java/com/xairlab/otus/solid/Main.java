package com.xairlab.otus.solid;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.put(new Pair(Banknote.FIFTY, 5));
        atm.get(20);
        atm.get(50);
    }
}