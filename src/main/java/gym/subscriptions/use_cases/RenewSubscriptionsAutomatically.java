package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;
import gym.subscriptions.domain.SubscriptionsRenewedAutomatically;

import java.time.LocalDate;

final class RenewSubscriptionsAutomatically {

    private final SubscriptionRepository subscriptionRepository;

    RenewSubscriptionsAutomatically(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    SubscriptionsRenewedAutomatically handle(RenewSubscriptionsAutomaticallyCommand command) {

        var endedSubscriptionsAsOf = subscriptionRepository.endedSubscriptions(
            LocalDate.parse(command.asOfDate)
        );

        endedSubscriptionsAsOf.values().forEach(
            Subscription::renew
        );

        subscriptionRepository.storeAll(endedSubscriptionsAsOf);

        return new SubscriptionsRenewedAutomatically(endedSubscriptionsAsOf);
    }
}
