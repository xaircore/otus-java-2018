package com.xairlab.otus.patterns.atm;

public enum Banknote {
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private int nominal;

    Banknote(int nominal) {
        this.nominal = nominal;
    }

    public static Banknote getByNominal(int nominal) {
        Banknote banknote;
        switch (nominal) {
            case 50:
                banknote = FIFTY;
                break;
            case 100:
                banknote = HUNDRED;
                break;
            case 200:
                banknote = TWO_HUNDRED;
                break;
            case 500:
                banknote = FIVE_HUNDRED;
                break;
            case 1000:
                banknote = THOUSAND;
                break;
            case 2000:
                banknote = TWO_THOUSAND;
                break;
            case 5000:
                banknote = FIVE_THOUSAND;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nominal);
        }
        return banknote;
    }

    public int getNominal() {
        return nominal;
    }

    @Override
    public String toString() {
        return String.valueOf(nominal);
    }
}
