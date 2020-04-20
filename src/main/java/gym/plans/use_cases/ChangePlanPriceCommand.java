package gym.plans.use_cases;

import gym.plans.domain.PlanId;

public final class ChangePlanPriceCommand {
    public final PlanId planId;
    public final Integer newPrice;

    public ChangePlanPriceCommand(PlanId planId, Integer newPrice) {
        this.planId = planId;
        this.newPrice = newPrice;
    }
}
