package gym.plans.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanTest {

    @Test
    public void a_plan_cannot_have_a_duration_other_than_1_month_or_12_months() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Plan.create("plan abc", 400, 4));
    }

    @Test
    public void a_plan_cannot_hava_a_negative_price() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Plan.create("plan abc", -42, 12));
    }

    @Test
    public void can_change_its_price() {
        var tested = Plan.create("plan abc", 400, 1);
        tested.changePrice(500);

        assertEquals(500, (int) tested.price.amount());
    }
}
