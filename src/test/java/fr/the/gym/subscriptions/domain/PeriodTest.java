package fr.the.gym.subscriptions.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PeriodTest {

    @Test
    public void can_be_before_a_date() {
        var tested = new Period(LocalDate.parse("2018-06-01"), 2);

        assertFalse(tested.beforeDate(LocalDate.parse("2018-07-31")));
        assertTrue(tested.beforeDate(LocalDate.parse("2018-08-01")));
    }

    @Test
    public void contains_a_date() {
        var tested = new Period("2018-06-01 until 2018-08-01");

        assertTrue(tested.contains(LocalDate.parse("2018-06-01")));
        assertTrue(tested.contains(LocalDate.parse("2018-06-02")));

        assertTrue(tested.contains(LocalDate.parse("2018-07-31")));
        assertFalse(tested.contains(LocalDate.parse("2018-08-01")));
    }

    @Test
    public void next() {
        var tested = new Period(LocalDate.parse("2018-06-01"), 2);

        assertEquals("2018-08-01 until 2018-09-30", tested.next().toString());
    }
}
