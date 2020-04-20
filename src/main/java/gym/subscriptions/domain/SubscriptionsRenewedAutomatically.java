package gym.subscriptions.domain;

import java.util.List;

public final class SubscriptionsRenewedAutomatically {

    public final List<SubscriptionId> renewedSubscriptions;

    public SubscriptionsRenewedAutomatically(List<SubscriptionId> renewedSubscriptions) {
        this.renewedSubscriptions = renewedSubscriptions;
    }
}
