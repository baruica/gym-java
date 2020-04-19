package fr.the.gym.plans.use_cases;

import fr.the.gym.plans.domain.NewPlanCreated;
import fr.the.gym.plans.domain.Plan;
import fr.the.gym.plans.domain.PlanException;
import fr.the.gym.plans.domain.PlanRepository;

final class CreateNewPlan {

    private final PlanRepository planRepository;

    CreateNewPlan(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    NewPlanCreated handle(CreateNewPlanCommand command) throws PlanException {

        var newPlan = Plan.create(
            command.planId,
            command.basePrice,
            command.planPeiodicity
        );

        planRepository.store(newPlan);

        return new NewPlanCreated(newPlan);
    }
}
