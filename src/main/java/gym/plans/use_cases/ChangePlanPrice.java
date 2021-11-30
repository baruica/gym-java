package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;

public record ChangePlanPrice(
    String planId,
    Integer newPrice
) {
    record Handler(
        PlanRepository planRepository
    ) {
        Plan handle(ChangePlanPrice command) {

            var plan = (Plan) planRepository.get(command.planId());

            plan.changePrice(command.newPrice());

            planRepository.store(plan);

            return plan;
        }
    }
}
