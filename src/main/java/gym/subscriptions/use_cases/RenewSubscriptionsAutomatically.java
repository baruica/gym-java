package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionEvent;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

final class RenewSubscriptionsAutomatically {

    private final SubscriptionRepository subscriptionRepository;

    RenewSubscriptionsAutomatically(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    List<SubscriptionEvent> handle(RenewSubscriptionsAutomaticallyCommand command) {

        var endedSubscriptionsAsOf = (List<Subscription>) subscriptionRepository.endedSubscriptions(
            LocalDate.parse(command.asOfDate)
        );

        endedSubscriptionsAsOf.forEach(
            Subscription::renew
        );

        subscriptionRepository.storeAll(endedSubscriptionsAsOf);

        return endedSubscriptionsAsOf.stream()
            .map(subscription -> subscription.getRaisedEvents().get(subscription.getRaisedEvents().size() - 1))
            .collect(Collectors.toList());
    }
}
