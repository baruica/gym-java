package gym.plans.use_cases;

import gym.plans.domain.PlanException;
import gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewPlanTest {

    @Test
    public void create_a_new_plan() throws PlanException {

        var repository = new PlanInMemoryRepository();
        var planId = repository.nextId();

        var tested = new CreateNewPlan(repository);

        var newPlan = tested.handle(
            new CreateNewPlanCommand(planId, 300, 1)
        );

        assertEquals(planId, newPlan.id.toString());
    }
}
