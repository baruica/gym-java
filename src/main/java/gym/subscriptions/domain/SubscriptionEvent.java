package gym.subscriptions.domain;

import common.DomainEvent;

import java.util.Objects;

public abstract class SubscriptionEvent implements DomainEvent {

    public final String subscriptionId;

    public SubscriptionEvent(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override
    public String aggregateId() {
        return subscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionEvent that = (SubscriptionEvent) o;
        return subscriptionId.equals(that.subscriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId);
    }
}
