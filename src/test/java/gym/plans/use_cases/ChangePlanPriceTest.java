package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.Plan.PlanId;
import gym.plans.domain.PlanRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangePlanPriceTest {

    @Test
    public void handle() {
        PlanRepository planRepository = new InMemoryPlanRepository();
        var planId = new PlanId(planRepository.nextId());

        planRepository.store(
            Plan.create(planId, 450, 12)
        );

        var tested = new ChangePlanPrice.Handler(planRepository);

        var plan = tested.handle(
            new ChangePlanPrice(planId, 400)
        );

        assertEquals(400, plan.price.amount());
    }

    @Test
    public void plan_not_found() {
        var planRepository = new InMemoryPlanRepository();

        var tested = new ChangePlanPrice.Handler(planRepository);

        assertThrows(RuntimeException.class, () -> tested.handle(
            new ChangePlanPrice(new PlanId("unknown planId"), 400)
        ));
    }
}
