package gym.plans.use_cases;

import gym.plans.domain.PlanId;
import gym.plans.domain.PlanPriceChanged;
import gym.plans.domain.PlanRepository;
import gym.plans.infrastructure.PlanRepositoryException;

final class ChangePlanPrice {

    private final PlanRepository planRepository;

    ChangePlanPrice(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    PlanPriceChanged handle(ChangePlanPriceCommand command) throws PlanRepositoryException {

        var plan = planRepository.get(new PlanId(command.planId));

        plan.changePrice(command.newPrice);

        planRepository.store(plan);

        return new PlanPriceChanged(plan);
    }
}
