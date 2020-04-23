package gym.plans.domain;

public final class PlanPriceChanged {

    public final String planId;

    public PlanPriceChanged(final Plan plan) {
        this.planId = plan.id.toString();
    }
}
