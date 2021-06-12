package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;

record ChangePlanPrice(
    PlanRepository planRepository
) {
    Plan handle(ChangePlanPriceCommand command) {

        var plan = (Plan) planRepository.get(command.planId());

        plan.changePrice(command.newPrice());

        planRepository.store(plan);

        return plan;
    }
}
