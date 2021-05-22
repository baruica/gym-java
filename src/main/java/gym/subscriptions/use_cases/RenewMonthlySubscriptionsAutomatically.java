package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

record RenewMonthlySubscriptionsAutomatically(
    SubscriptionRepository subscriptionRepository
) {
    List<Subscription> handle(RenewMonthlySubscriptionsAutomaticallyCommand command) {

        var endedMonthlySubscriptionsAsOf = (List<Subscription>) subscriptionRepository.endedMonthlySubscriptions(
            LocalDate.parse(command.asOfDate())
        );

        endedMonthlySubscriptionsAsOf.forEach(
            Subscription::renew
        );

        subscriptionRepository.storeAll(endedMonthlySubscriptionsAsOf);

        return endedMonthlySubscriptionsAsOf;
    }
}
