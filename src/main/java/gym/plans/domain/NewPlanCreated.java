package gym.plans.domain;

import java.util.Objects;

public final class NewPlanCreated extends PlanEvent {

    public final String planId;
    public final Integer planPrice;
    public final Integer planDuration;

    public NewPlanCreated(final String planId, final Integer planPrice, final Integer planDuration) {
        super(planId);
        this.planId = planId;
        this.planPrice = planPrice;
        this.planDuration = planDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NewPlanCreated that = (NewPlanCreated) o;
        return planId.equals(that.planId) &&
            planPrice.equals(that.planPrice) &&
            planDuration.equals(that.planDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), planId, planPrice, planDuration);
    }
}
