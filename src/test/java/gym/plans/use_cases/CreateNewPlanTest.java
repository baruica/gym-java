package gym.plans.use_cases;

import gym.plans.domain.NewPlanCreated;
import gym.plans.domain.PlanException;
import gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateNewPlanTest {

    @Test
    public void create_a_new_plan() throws PlanException {

        PlanInMemoryRepository planRepository = new PlanInMemoryRepository();

        var tested = new CreateNewPlan(planRepository);

        var events = tested.handle(
            new CreateNewPlanCommand(300, 1)
        );

        assertEquals(
            events.get(events.size() - 1),
            new NewPlanCreated(
                events.get(events.size() - 1).aggregateId,
                300,
                1
            )
        );
    }
}
