package gym.plans.use_cases;

import gym.plans.domain.*;

final class CreateNewPlan {

    private final PlanRepository planRepository;

    CreateNewPlan(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    NewPlanCreated handle(CreateNewPlanCommand command) throws PlanException {

        var newPlan = new Plan(
            new PlanId(command.planId),
            command.basePrice,
            command.planDurationsInMonths
        );

        planRepository.store(newPlan);

        return new NewPlanCreated(newPlan);
    }
}
