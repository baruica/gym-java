package gym.subscriptions.infrastructure;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionId;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.HashMap;
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
    public void storeAll(Map<SubscriptionId, Subscription> subscriptions) {
        subscriptions.forEach((subscriptionId, subscription) -> store(subscription));
    }

    @Override
    public Subscription get(SubscriptionId subscriptionId) throws SubscriptionRepositoryException {

        if (subscriptions.containsKey(subscriptionId)) {
            return subscriptions.get(subscriptionId);
        }

        throw SubscriptionRepositoryException.notFound(subscriptionId);
    }

    @Override
    public Map<SubscriptionId, Subscription> ongoingSubscriptions(LocalDate asOfDate) {

        return subscriptions.entrySet().stream()
            .filter(subscription -> subscription.getValue().isOngoing(asOfDate))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<SubscriptionId, Subscription> endedSubscriptions(LocalDate date) {

        return subscriptions.entrySet().stream()
            .filter(subscription -> subscription.getValue().willBeEnded(date))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
