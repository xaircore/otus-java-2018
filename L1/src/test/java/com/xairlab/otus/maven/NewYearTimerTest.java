package com.xairlab.otus.maven;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class NewYearTimerTest {

    @Test
    void positive(){
        int days = com.xairlab.otus.maven.NewYearTimer.daysToNewYear().getDays();
        assertTrue(days >= 0, "Время до нового года не может быть отрицательным");
    }
}