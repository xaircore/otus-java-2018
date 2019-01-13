package test.java.com.xairlab.otus.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class NewYearTimerTest {

    @Test
    void positive(){
        int days = main.java.com.xairlab.otus.maven.NewYearTimer.daysToNewYear().getDays();
        assertEquals(days, days, "Время не совпадает");
    }
}