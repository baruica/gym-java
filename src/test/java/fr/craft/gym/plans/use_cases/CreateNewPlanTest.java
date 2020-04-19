package fr.craft.gym.plans.use_cases;

import fr.craft.gym.plans.domain.PlanException;
import fr.craft.gym.plans.domain.PlanId;
import fr.craft.gym.plans.domain.PlanPeriodicity;
import fr.craft.gym.plans.infrastructure.PlanInMemoryRepository;
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
            new CreateNewPlanCommand(planId, 300, PlanPeriodicity.MONTHLY)
        );

        Assert.assertEquals(planId, event.planId);
    }
}
