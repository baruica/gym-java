package gym.plans.domain;

public final class NewPlanCreated {

    public final String planId;

    public NewPlanCreated(final Plan plan) {
        this.planId = plan.id.toString();
    }
}
