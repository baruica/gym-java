package gym.plans.domain;

import common.DomainEvent;

import java.util.Objects;

public abstract class PlanEvent implements DomainEvent {

    public final String planId;

    public PlanEvent(String planId) {
        this.planId = planId;
    }

    @Override
    public String aggregateId() {
        return planId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanEvent planEvent = (PlanEvent) o;
        return planId.equals(planEvent.planId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId);
    }
}
