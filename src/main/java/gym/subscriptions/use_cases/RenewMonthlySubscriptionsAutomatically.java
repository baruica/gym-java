package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

public record RenewMonthlySubscriptionsAutomatically(String asOfDate) {
    record Handler(
        SubscriptionRepository subscriptionRepository
    ) {
        List<Subscription> handle(RenewMonthlySubscriptionsAutomatically command) {

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
}
