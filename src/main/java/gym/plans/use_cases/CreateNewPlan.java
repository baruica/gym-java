package gym.plans.use_cases;

import gym.plans.domain.NewPlanCreated;
import gym.plans.domain.Plan;
import gym.plans.domain.PlanException;
import gym.plans.domain.PlanRepository;

final class CreateNewPlan {

    private final PlanRepository planRepository;

    CreateNewPlan(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    NewPlanCreated handle(CreateNewPlanCommand command) throws PlanException {

        var newPlan = Plan.create(
            command.planId,
            command.basePrice,
            command.planDurationsInMonths
        );

        planRepository.store(newPlan);

        return new NewPlanCreated(newPlan);
    }
}
