package gym.subscriptions.domain;

import java.util.Objects;

public final class NewSubscription extends SubscriptionEvent {

    public final String subscriptionStartDate;
    public final String email;

    public NewSubscription(final String subscriptionId, final String subscriptionStartDate, final String email) {
        super(subscriptionId);
        this.subscriptionStartDate = subscriptionStartDate;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewSubscription that = (NewSubscription) o;
        return subscriptionId.equals(that.subscriptionId) &&
            subscriptionStartDate.equals(that.subscriptionStartDate) &&
            email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId, subscriptionStartDate, email);
    }
}
