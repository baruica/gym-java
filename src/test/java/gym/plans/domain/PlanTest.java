package gym.plans.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanTest {

    @Test(expected = PlanException.class)
    public void a_plan_cannot_have_a_duration_other_than_1_month_or_12_months() throws PlanException {
        new Plan(new PlanId("abc"), 400, 4);
    }

    @Test(expected = PlanException.class)
    public void a_plan_cannot_hava_a_negative_price() throws PlanException {
        new Plan(new PlanId("abc"), -42, 12);
    }

    @Test
    public void can_change_its_price() throws PlanException {
        var tested = new Plan(new PlanId("abc"), 400, 1);
        tested.changePrice(500);

        assertEquals(new Price(500), tested.price);
    }
}
