package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;
import gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangePlanPriceTest {

    @Test
    public void handle() {
        PlanRepository planRepository = new PlanInMemoryRepository();
        var planId = planRepository.nextId();

        planRepository.store(
            Plan.create(planId, 450, 12)
        );

        var tested = new ChangePlanPrice(planRepository);

        var plan = tested.handle(
            new ChangePlanPriceCommand(planId, 400)
        );

        assertEquals(400, plan.price.amount());
    }

    @Test
    public void plan_not_found() {
        var planRepository = new PlanInMemoryRepository();

        var tested = new ChangePlanPrice(planRepository);

        assertThrows(RuntimeException.class, () -> tested.handle(
            new ChangePlanPriceCommand("unknown planId", 400)
        ));
    }
}
