package com.xairlab.otus.solid;

public class Pair {

    private final Banknote banknote;
    private final int count;

    public Pair(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getCount() {
        return count;
    }
}
