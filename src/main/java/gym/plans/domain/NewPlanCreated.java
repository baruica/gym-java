package gym.plans.domain;

import java.util.Objects;

public final class NewPlanCreated extends PlanEvent {

    public final Integer planPrice;
    public final Integer planDurationInMonths;

    public NewPlanCreated(final String planId, final Integer planPrice, final Integer planDurationInMonths) {
        super(planId);
        this.planPrice = planPrice;
        this.planDurationInMonths = planDurationInMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NewPlanCreated that = (NewPlanCreated) o;
        return planId.equals(that.planId) &&
            planPrice.equals(that.planPrice) &&
            planDurationInMonths.equals(that.planDurationInMonths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), planId, planPrice, planDurationInMonths);
    }
}
