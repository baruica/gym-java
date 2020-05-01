package gym.subscriptions.domain;

import java.time.LocalDate;

public final class NewSubscription {

    public final String subscriptionId;
    public final String subscriptionStartDate;
    public final String email;

    public NewSubscription(final SubscriptionId subscriptionId, final LocalDate subscriptionStartDate, final String email) {
        this.subscriptionId = subscriptionId.toString();
        this.subscriptionStartDate = subscriptionStartDate.toString();
        this.email = email;
    }
}
