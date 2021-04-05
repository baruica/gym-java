package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class InMemorySubscriptionRepository implements SubscriptionRepository {

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
    public List<Subscription> ongoingSubscriptions(LocalDate date) {

        return subscriptions.values().stream()
            .filter(subscription -> subscription.isOngoing(date))
            .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> endedMonthlySubscriptions(LocalDate date) {

        return subscriptions.values().stream()
            .filter(Subscription::isMonthly)
            .filter(subscription -> subscription.willBeEndedAfter(date))
            .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> threeYearsAnniversarySubscriptions(LocalDate date) {

        return subscriptions.values().stream()
            .filter(subscription -> subscription.hasThreeYearsAnniversaryOn(date))
            .collect(Collectors.toList());
    }
}
