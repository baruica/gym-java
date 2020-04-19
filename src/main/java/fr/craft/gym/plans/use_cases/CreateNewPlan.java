package fr.craft.gym.plans.use_cases;

import fr.craft.gym.plans.domain.NewPlanCreated;
import fr.craft.gym.plans.domain.Plan;
import fr.craft.gym.plans.domain.PlanException;
import fr.craft.gym.plans.domain.PlanRepository;

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
