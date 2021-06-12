package gym.subscriptions.use_cases;

import gym.InMemoryRepository;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public final class InMemorySubscriptionRepository extends InMemoryRepository<Subscription> implements SubscriptionRepository {

    @Override
    public List<Subscription> ongoingSubscriptions(LocalDate date) {

        return items.values().stream()
            .filter(subscription -> subscription.isOngoing(date))
            .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> endedMonthlySubscriptions(LocalDate date) {

        return items.values().stream()
            .filter(Subscription::isMonthly)
            .filter(subscription -> subscription.willBeEndedAsFrom(date))
            .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> threeYearsAnniversarySubscriptions(LocalDate date) {

        return items.values().stream()
            .filter(subscription -> subscription.hasThreeYearsAnniversaryAfter(date))
            .collect(Collectors.toList());
    }
}
