package gym.subscriptions.infrastructure;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class SubscriptionInMemoryRepository implements SubscriptionRepository {

    private final Map<Subscription.SubscriptionId, Subscription> subscriptions = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Subscription subscription) {
        subscriptions.put(subscription.id, subscription);
    }

    @Override
    public void storeAll(List<? extends Subscription> subscriptions) {
        subscriptions.forEach(this::store);
    }

    @Override
    public List<Subscription> ongoingSubscriptions(LocalDate asOfDate) {

        return subscriptions.values().stream()
            .filter(subscription -> subscription.isOngoing(asOfDate))
            .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> endedMonthlySubscriptions(LocalDate date) {

        return subscriptions.values().stream()
            .filter(Subscription::isMonthly)
            .filter(subscription -> subscription.willBeEndedAfter(date))
            .collect(Collectors.toList());
    }
}
