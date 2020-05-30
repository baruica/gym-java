package gym.subscriptions.domain;

import java.util.Objects;

public final class SubscriptionRenewed extends SubscriptionEvent {

    public final String oldEndDate;
    public final String newEndDate;

    public SubscriptionRenewed(String subscriptionId, String oldEndDate, String newEndDate) {
        super(subscriptionId);
        this.oldEndDate = oldEndDate;
        this.newEndDate = newEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionRenewed that = (SubscriptionRenewed) o;
        return subscriptionId.equals(that.subscriptionId) &&
            oldEndDate.equals(that.oldEndDate) &&
            newEndDate.equals(that.newEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId, oldEndDate, newEndDate);
    }
}
