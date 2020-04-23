package gym.subscriptions.domain;

import gym.AggregateId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class SubscriptionsRenewedAutomatically {

    public final List<String> renewedSubscriptions;

    public SubscriptionsRenewedAutomatically(Map<SubscriptionId, Subscription> renewedSubscriptions) {
        this.renewedSubscriptions = new ArrayList<>(renewedSubscriptions.keySet()).stream()
            .map(AggregateId::toString)
            .collect(Collectors.toList());
    }
}
