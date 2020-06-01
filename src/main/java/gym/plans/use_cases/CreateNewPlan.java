package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanException;
import gym.plans.domain.PlanId;
import gym.plans.domain.PlanRepository;

final class CreateNewPlan {

    private final PlanRepository planRepository;

    CreateNewPlan(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    Plan handle(CreateNewPlanCommand command) throws PlanException {

        var newPlan = Plan.create(
            new PlanId(command.planId),
            command.basePrice,
            command.planDurationsInMonths
        );

        planRepository.store(newPlan);

        return newPlan;
    }
}
