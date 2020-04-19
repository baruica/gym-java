package fr.the.gym.plans.use_cases;

import fr.the.gym.plans.domain.PlanException;
import fr.the.gym.plans.domain.PlanId;
import fr.the.gym.plans.domain.PlanPeriodicity;
import fr.the.gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateNewPlanTest {

    @Test
    public void create_a_new_plan() throws PlanException {

        PlanInMemoryRepository planRepository = new PlanInMemoryRepository();
        PlanId planId = planRepository.nextId();

        var tested = new CreateNewPlan(
            planRepository
        );

        var event = tested.handle(
            new CreateNewPlanCommand(planId, 300, PlanPeriodicity.MONTHLY)
        );

        assertEquals(planId, event.planId);
    }
}
