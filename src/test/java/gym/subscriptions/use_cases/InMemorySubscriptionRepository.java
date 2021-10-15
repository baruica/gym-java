package gym.subscriptions.use_cases;

import gym.InMemoryRepository;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

public final class InMemorySubscriptionRepository extends InMemoryRepository<Subscription> implements SubscriptionRepository {

    @Override
    public List<Subscription> ongoingSubscriptions(LocalDate date) {

        return aggregates.values().stream()
            .filter(subscription -> subscription.isOngoing(date))
            .toList();
    }

    @Override
    public List<Subscription> endedMonthlySubscriptions(LocalDate date) {

        return aggregates.values().stream()
            .filter(Subscription::isMonthly)
            .filter(subscription -> subscription.willBeEndedAsFrom(date))
            .toList();
    }

    @Override
    public List<Subscription> threeYearsAnniversarySubscriptions(LocalDate date) {

        return aggregates.values().stream()
            .filter(subscription -> subscription.hasThreeYearsAnniversaryAfter(date))
            .toList();
    }
}
