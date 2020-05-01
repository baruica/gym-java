package gym.plans.use_cases;

import gym.plans.domain.PlanEvent;
import gym.plans.domain.PlanException;
import gym.plans.domain.PlanId;
import gym.plans.domain.PlanRepository;
import gym.plans.infrastructure.PlanRepositoryException;

import java.util.List;

final class ChangePlanPrice {

    private final PlanRepository planRepository;

    ChangePlanPrice(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    List<PlanEvent> handle(ChangePlanPriceCommand command) throws PlanRepositoryException, PlanException {

        var plan = planRepository.get(new PlanId(command.planId));

        plan.changePrice(command.newPrice);

        planRepository.store(plan);

        return plan.getRaisedEvents();
    }
}
