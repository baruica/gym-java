package fr.craft.gym.plans.use_cases;

import fr.craft.gym.plans.domain.PlanId;

public final class ChangePlanPriceCommand {
    public final PlanId planId;
    public final Integer newPrice;

    public ChangePlanPriceCommand(PlanId planId, Integer newPrice) {
        this.planId = planId;
        this.newPrice = newPrice;
    }
}
