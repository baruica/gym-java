package gym.subscriptions.domain;

import java.time.LocalDate;

public final class PlanSubscribed {

    public final SubscriptionId subscriptionId;
    public final LocalDate subscriptionStartDate;
    public final String email;

    public PlanSubscribed(final SubscriptionId subscriptionId, final LocalDate subscriptionStartDate, final String email) {
        this.subscriptionId = subscriptionId;
        this.subscriptionStartDate = subscriptionStartDate;
        this.email = email;
    }
}
