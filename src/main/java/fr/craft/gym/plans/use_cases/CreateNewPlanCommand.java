package fr.craft.gym.plans.use_cases;

import fr.craft.gym.plans.domain.PlanId;
import fr.craft.gym.plans.domain.PlanPeriodicity;

public final class CreateNewPlanCommand {
    public final PlanId planId;
    public final Integer basePrice;
    public final PlanPeriodicity planPeiodicity;

    public CreateNewPlanCommand(PlanId planId, Integer basePrice, PlanPeriodicity planPeiodicity) {
        this.planId = planId;
        this.basePrice = basePrice;
        this.planPeiodicity = planPeiodicity;
    }
}
