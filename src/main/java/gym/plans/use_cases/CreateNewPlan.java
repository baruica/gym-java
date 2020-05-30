package gym.plans.use_cases;

import gym.plans.domain.*;

import java.util.List;

final class CreateNewPlan {

    private final PlanRepository planRepository;

    CreateNewPlan(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    List<PlanEvent> handle(CreateNewPlanCommand command) throws PlanException {

        var newPlan = Plan.create(
            new PlanId(planRepository.nextId()),
            command.basePrice,
            command.planDurationsInMonths
        );

        planRepository.store(newPlan);

        return newPlan.getRaisedEvents();
    }
}
