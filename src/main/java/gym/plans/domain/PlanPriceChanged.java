package gym.plans.domain;

import java.util.Objects;

public final class PlanPriceChanged extends PlanEvent {

    public final Integer oldPrice;
    public final Integer newPrice;

    public PlanPriceChanged(String planId, Integer oldPrice, Integer newPrice) {
        super(planId);
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlanPriceChanged that = (PlanPriceChanged) o;
        return planId.equals(that.planId) &&
            oldPrice.equals(that.oldPrice) &&
            newPrice.equals(that.newPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), planId, oldPrice, newPrice);
    }
}
