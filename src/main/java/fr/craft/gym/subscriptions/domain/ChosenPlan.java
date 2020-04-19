package fr.craft.gym.subscriptions.domain;

import fr.craft.gym.plans.domain.PlanId;

public final class ChosenPlan {

    private final PlanId planId;
    final Integer price;
    final Integer durationInMonths;
    final String description;

    public ChosenPlan(PlanId planId, final Integer price, final Integer durationInMonths, final String description) {
        this.planId = planId;
        this.price = price;
        this.durationInMonths = durationInMonths;
        this.description = description;
    }

    Boolean isYearly() {
        return durationInMonths == 12;
    }
}
