package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.Plan.PlanId;
import gym.plans.domain.PlanRepository;

public record CreateNewPlan(
    PlanId planId,
    Integer basePrice,
    Integer planDurationsInMonths
) {
    record Handler(
        PlanRepository planRepository
    ) {
        Plan handle(CreateNewPlan command) {

            var newPlan = Plan.create(
                command.planId(),
                command.basePrice(),
                command.planDurationsInMonths()
            );

            planRepository.store(newPlan);

            return newPlan;
        }
    }
}
