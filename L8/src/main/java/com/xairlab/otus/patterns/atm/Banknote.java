package com.xairlab.otus.patterns.atm;

public enum Banknote {
    FIFTY,
    HUNDRED,
    TWO_HUNDRED,
    FIVE_HUNDRED,
    THOUSAND,
    TWO_THOUSAND,
    FIVE_THOUSAND;

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

    public static int getByBanknote(Banknote banknote) {
        int nominal;
        switch (banknote) {
            case FIFTY:
                nominal = 50;
                break;
            case HUNDRED:
                nominal = 100;
                break;
            case TWO_HUNDRED:
                nominal = 200;
                break;
            case FIVE_HUNDRED:
                nominal = 500;
                break;
            case THOUSAND:
                nominal = 1000;
                break;
            case TWO_THOUSAND:
                nominal = 2000;
                break;
            case FIVE_THOUSAND:
                nominal = 5000;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + banknote);
        }
        return nominal;
    }

    @Override
    public String toString() {
        String result = "";
        switch (this) {
            case FIFTY:
                result = "50";
                break;
            case HUNDRED:
                result = "100";
                break;
            case TWO_HUNDRED:
                result = "200";
                break;
            case FIVE_HUNDRED:
                result = "500";
                break;
            case THOUSAND:
                result = "1000";
                break;
            case TWO_THOUSAND:
                result = "2000";
                break;
            case FIVE_THOUSAND:
                result = "5000";
                break;
        }
        return result;
    }
}
