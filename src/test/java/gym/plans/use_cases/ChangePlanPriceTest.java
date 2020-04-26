package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanException;
import gym.plans.infrastructure.PlanInMemoryRepository;
import gym.plans.infrastructure.PlanRepositoryException;
import org.junit.Test;

public class ChangePlanPriceTest {

    @Test
    public void handle() throws PlanRepositoryException, PlanException {
        var planRepository = new PlanInMemoryRepository();
        var planId = planRepository.nextId();

        planRepository.store(
            new Plan(planId, 450, 12)
        );

        var tested = new ChangePlanPrice(planRepository);

        var event = tested.handle(
            new ChangePlanPriceCommand(planId.toString(), 400)
        );

        //assertEquals(400, (int) planRepository.get(new PlanId(event.planId)).price);
    }
}
