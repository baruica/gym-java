package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;
import gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewPlanTest {

    @Test
    public void create_a_new_plan() {

        PlanRepository repository = new PlanInMemoryRepository();
        var planId = repository.nextId();

        var tested = new CreateNewPlan(repository);

        var newPlan = tested.handle(
            new CreateNewPlanCommand(planId, 300, 1)
        );

        assertEquals(new Plan.PlanId(planId), newPlan.id);
    }
}
