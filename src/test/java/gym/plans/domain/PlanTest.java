package gym.plans.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlanTest {

    @Test
    public void has_a_factory_method_to_get_specific_type_of_plan() throws PlanException {
        var yearlyPlan = Plan.create(new PlanId("abc"), 600, 12);
        assertTrue(yearlyPlan instanceof Plan);

        var monthlyPlan = Plan.create(new PlanId("def"), 200, 1);
        assertTrue(monthlyPlan instanceof Plan);
    }

    @Test(expected = PlanException.class)
    public void a_plan_cannot_be_anything_other_than_monthly_or_yearly() throws PlanException {
        Plan.create(new PlanId("abc"), 400, 4);
    }

    @Test(expected = PlanException.class)
    public void has_a_valid_price() throws PlanException {
        Plan.create(new PlanId("abc"), -42, 12);
    }

    @Test
    public void can_change_its_price() throws PlanException {
        var tested = Plan.create(new PlanId("abc"), 400, 1);
        tested.changePrice(500);

        assertEquals(new PlanId("abc"), tested.id);
        assertEquals(Integer.valueOf(500), tested.price);
    }
}
