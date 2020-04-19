package fr.craft.gym.plans.domain;

public final class NewPlanCreated {

    public final PlanId planId;

    public NewPlanCreated(final Plan plan) {
        this.planId = plan.id;
    }
}
