package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;

record CreateNewPlan(
    PlanRepository planRepository
) {
    Plan handle(CreateNewPlanCommand command) {

        var newPlan = Plan.create(
            command.planId(),
            command.basePrice(),
            command.planDurationsInMonths()
        );

        planRepository.store(newPlan);

        return newPlan;
    }
}
