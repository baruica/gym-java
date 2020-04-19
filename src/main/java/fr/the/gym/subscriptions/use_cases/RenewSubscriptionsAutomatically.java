package fr.the.gym.subscriptions.use_cases;

import fr.the.gym.subscriptions.domain.SubscriptionRepository;
import fr.the.gym.subscriptions.domain.SubscriptionsRenewedAutomatically;

import java.util.ArrayList;

final class RenewSubscriptionsAutomatically {

    private final SubscriptionRepository subscriptionRepository;

    RenewSubscriptionsAutomatically(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    SubscriptionsRenewedAutomatically handle(RenewSubscriptionsAutomaticallyCommand command) {

        var endedSubscriptionsAsOf = subscriptionRepository.endedSubscriptions(command.asOfDate);

        endedSubscriptionsAsOf.forEach((subscriptionId, subscription) -> subscription.renew());

        subscriptionRepository.storeAll(endedSubscriptionsAsOf);

        return new SubscriptionsRenewedAutomatically(
            new ArrayList<>(endedSubscriptionsAsOf.keySet())
        );
    }
}
