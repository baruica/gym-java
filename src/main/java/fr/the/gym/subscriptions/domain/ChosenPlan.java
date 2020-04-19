package fr.the.gym.subscriptions.domain;

import fr.the.gym.plans.domain.PlanId;

public final class ChosenPlan {

    private final PlanId planId;
    private final Integer price;
    private final Integer durationInMonths;
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

    Integer price() {
        return price;
    }

    Integer durationInMonths() {
        return durationInMonths;
    }
}
