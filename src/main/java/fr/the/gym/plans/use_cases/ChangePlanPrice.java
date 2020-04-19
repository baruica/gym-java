package fr.the.gym.plans.use_cases;

import fr.the.gym.plans.domain.PlanPriceChanged;
import fr.the.gym.plans.domain.PlanRepository;
import fr.the.gym.plans.domain.PlanRepositoryException;

final class ChangePlanPrice {

    private final PlanRepository planRepository;

    ChangePlanPrice(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    PlanPriceChanged handle(ChangePlanPriceCommand command) throws PlanRepositoryException {

        var plan = planRepository.get(command.planId);

        plan.changePrice(command.newPrice);

        planRepository.store(plan);

        return new PlanPriceChanged(plan);
    }
}
