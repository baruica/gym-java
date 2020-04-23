package gym.plans.use_cases;

import gym.plans.domain.PlanException;
import gym.plans.domain.PlanId;
import gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.Assert;
import org.junit.Test;

public class CreateNewPlanTest {

    @Test
    public void create_a_new_plan() throws PlanException {

        PlanInMemoryRepository planRepository = new PlanInMemoryRepository();
        PlanId planId = planRepository.nextId();

        var tested = new CreateNewPlan(
            planRepository
        );

        var event = tested.handle(
            new CreateNewPlanCommand(planId, 300, 1)
        );

        Assert.assertEquals(planId, event.planId);
    }
}
