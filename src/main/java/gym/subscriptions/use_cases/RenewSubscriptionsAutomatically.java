package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

final class RenewSubscriptionsAutomatically {

    private final SubscriptionRepository subscriptionRepository;

    RenewSubscriptionsAutomatically(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    List<Subscription> handle(RenewSubscriptionsAutomaticallyCommand command) {

        var endedSubscriptionsAsOf = (List<Subscription>) subscriptionRepository.endedSubscriptions(
            LocalDate.parse(command.asOfDate)
        );

        endedSubscriptionsAsOf.forEach(
            Subscription::renew
        );

        subscriptionRepository.storeAll(endedSubscriptionsAsOf);

        return endedSubscriptionsAsOf;
    }
}
