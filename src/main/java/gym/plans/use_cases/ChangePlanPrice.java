package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;

final class ChangePlanPrice {

    private final PlanRepository planRepository;

    ChangePlanPrice(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    Plan handle(ChangePlanPriceCommand command) {

        var plan = (Plan) planRepository.get(new Plan.PlanId(command.planId()));

        plan.changePrice(command.newPrice());

        planRepository.store(plan);

        return plan;
    }
}
