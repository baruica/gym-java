package fr.the.gym.plans.use_cases;

import fr.the.gym.plans.domain.Plan;
import fr.the.gym.plans.domain.PlanException;
import fr.the.gym.plans.domain.PlanPeriodicity;
import fr.the.gym.plans.domain.PlanRepositoryException;
import fr.the.gym.plans.infrastructure.PlanInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangePlanPriceTest {

    @Test
    public void handle() throws PlanRepositoryException, PlanException {
        var planRepository = new PlanInMemoryRepository();
        var planId = planRepository.nextId();

        planRepository.store(
            Plan.create(planId, 450, PlanPeriodicity.YEARLY)
        );

        var tested = new ChangePlanPrice(planRepository);

        var event = tested.handle(
            new ChangePlanPriceCommand(planId, 400)
        );

        assertEquals(400, (int) event.plan.price());
    }
}
