package main.java.com.xairlab.otus.maven;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class NewYearTimer {
    public static void main(String... args) {
        System.out.println("До Нового года осталось " + daysToNewYear().getDays() + " дней");
    }

    public static Days daysToNewYear() {
        LocalDate currentDate = new LocalDate();
        LocalDate newYear = currentDate.plusYears(1).withDayOfYear(1);
        return Days.daysBetween(currentDate, newYear);
    }
}
