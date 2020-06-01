package gym.plans.use_cases;

import common.RepositoryException;
import gym.plans.domain.Plan;
import gym.plans.domain.PlanException;
import gym.plans.domain.PlanId;
import gym.plans.domain.PlanRepository;

final class ChangePlanPrice {

    private final PlanRepository planRepository;

    ChangePlanPrice(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    Plan handle(ChangePlanPriceCommand command) throws RepositoryException, PlanException {

        var plan = (Plan) planRepository.get(new PlanId(command.planId));

        plan.changePrice(command.newPrice);

        planRepository.store(plan);

        return plan;
    }
}
