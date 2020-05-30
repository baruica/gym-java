package gym.plans.use_cases;

import common.RepositoryException;
import gym.plans.domain.*;

import java.util.List;

final class ChangePlanPrice {

    private final PlanRepository planRepository;

    ChangePlanPrice(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    List<PlanEvent> handle(ChangePlanPriceCommand command) throws RepositoryException, PlanException {

        var plan = (Plan) planRepository.get(new PlanId(command.planId));

        plan.changePrice(command.newPrice);

        planRepository.store(plan);

        return plan.getRaisedEvents();
    }
}
