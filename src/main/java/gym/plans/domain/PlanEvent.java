package gym.plans.domain;

import java.util.Objects;

public class PlanEvent {

    public final String aggregateId;

    public PlanEvent(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanEvent planEvent = (PlanEvent) o;
        return aggregateId.equals(planEvent.aggregateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId);
    }
}
