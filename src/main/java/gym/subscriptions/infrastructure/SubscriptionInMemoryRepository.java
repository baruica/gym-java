package gym.subscriptions.infrastructure;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionId;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class SubscriptionInMemoryRepository implements SubscriptionRepository {

    private final Map<SubscriptionId, Subscription> subscriptions = new HashMap<>();

    @Override
    public SubscriptionId nextId() {
        return new SubscriptionId(UUID.randomUUID().toString());
    }

    @Override
    public void store(Subscription subscription) {
        subscriptions.put(subscription.id, subscription);
    }

    @Override
    public void storeAll(List<Subscription> subscriptions) {
        subscriptions.forEach(this::store);
    }

    @Override
    public Subscription get(SubscriptionId subscriptionId) throws SubscriptionRepositoryException {

        if (subscriptions.containsKey(subscriptionId)) {
            return subscriptions.get(subscriptionId);
        }

        throw SubscriptionRepositoryException.notFound(subscriptionId);
    }

    @Override
    public List<Subscription> ongoingSubscriptions(LocalDate asOfDate) {

        return subscriptions.values().stream()
            .filter(subscription -> subscription.isOngoing(asOfDate))
            .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> endedSubscriptions(LocalDate date) {

        return subscriptions.values().stream()
            .filter(subscription -> subscription.willBeEndedAfter(date))
            .collect(Collectors.toList());
    }
}
