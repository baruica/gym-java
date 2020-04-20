package gym.subscriptions.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PeriodTest {

    @Test
    public void can_be_before_a_date() {
        var tested = new Period(LocalDate.parse("2018-06-01"), 2);

        assertFalse(tested.isBefore(LocalDate.parse("2018-07-31")));
        assertTrue(tested.isBefore(LocalDate.parse("2018-08-01")));
    }

    @Test
    public void contains_a_date() {
        var tested = new Period(LocalDate.parse("2018-06-01"), 2);

        assertTrue(tested.contains(LocalDate.parse("2018-06-01")));
        assertTrue(tested.contains(LocalDate.parse("2018-06-02")));

        assertTrue(tested.contains(LocalDate.parse("2018-07-31")));
        assertFalse(tested.contains(LocalDate.parse("2018-08-01")));
    }

    @Test
    public void next() {
        var tested = new Period(LocalDate.parse("2018-06-01"), 2);

        assertTrue(tested.next().contains(LocalDate.parse("2018-09-30")));
        assertFalse(tested.next().contains(LocalDate.parse("2018-10-01")));
    }
}
