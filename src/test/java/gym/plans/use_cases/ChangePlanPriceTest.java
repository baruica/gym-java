package gym.plans.use_cases;

import common.RepositoryException;
import gym.plans.domain.Plan;
import gym.plans.domain.PlanException;
import gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePlanPriceTest {

    @Test
    public void handle() throws RepositoryException, PlanException {
        var planRepository = new PlanInMemoryRepository();
        var planId = planRepository.nextId();

        planRepository.store(
            Plan.create(planId, 450, 12)
        );

        var tested = new ChangePlanPrice(planRepository);

        var plan = tested.handle(
            new ChangePlanPriceCommand(planId, 400)
        );

        assertEquals(400, plan.price.amount);
    }
}
