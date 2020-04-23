package gym.plans.use_cases;

import gym.plans.domain.PlanId;

public final class CreateNewPlanCommand {
    public final PlanId planId;
    public final Integer basePrice;
    public final Integer planDurationsInMonths;

    public CreateNewPlanCommand(PlanId planId, Integer basePrice, Integer planDurationsInMonths) {
        this.planId = planId;
        this.basePrice = basePrice;
        this.planDurationsInMonths = planDurationsInMonths;
    }
}
