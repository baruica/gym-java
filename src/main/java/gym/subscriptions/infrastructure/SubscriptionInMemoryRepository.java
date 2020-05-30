package gym.subscriptions.infrastructure;

import common.Aggregate;
import common.AggregateId;
import common.RepositoryException;
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
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Aggregate aggregate) {
        subscriptions.put((SubscriptionId) aggregate.id(), (Subscription) aggregate);
    }

    @Override
    public void storeAll(List<? extends Aggregate> aggregates) {
        aggregates.forEach(this::store);
    }

    @Override
    public Aggregate get(AggregateId aggregateId) throws RepositoryException {
        if (subscriptions.containsKey((SubscriptionId) aggregateId)) {
            return subscriptions.get(aggregateId);
        }

        throw RepositoryException.notFound(aggregateId);
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
