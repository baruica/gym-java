package gym.plans.use_cases;

import gym.plans.domain.Plan.PlanId;
import gym.plans.domain.PlanRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewPlanTest {

    @Test
    public void create_a_new_plan() {

        PlanRepository repository = new InMemoryPlanRepository();
        var planId = new PlanId(repository.nextId());

        var tested = new CreateNewPlan.Handler(repository);

        var newPlan = tested.handle(
            new CreateNewPlan(planId, 300, 1)
        );

        assertEquals(planId, newPlan.id);
    }
}
