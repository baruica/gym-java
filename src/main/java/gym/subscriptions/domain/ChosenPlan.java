package gym.subscriptions.domain;

import gym.plans.domain.PlanId;

final class ChosenPlan {

    final PlanId planId;
    final Integer price;
    final Integer durationInMonths;

    public ChosenPlan(PlanId planId, final Integer price, final Integer durationInMonths) {
        this.planId = planId;
        this.price = price;
        this.durationInMonths = durationInMonths;
    }

    Boolean isYearly() {
        return durationInMonths == 12;
    }
}
