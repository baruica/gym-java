package gym.reporting.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

final class TurnoverForAGivenMonth {

    private final SubscriptionRepository subscriptionRepository;

    TurnoverForAGivenMonth(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    Double handle(TurnoverForAGivenMonthQuery query) {

        var asOfDate = LocalDate.parse(query.asOfDate);

        return subscriptionRepository.ongoingSubscriptions(asOfDate)
            .stream()
            .map(Subscription::monthlyTurnover)
            .reduce(0.0, Double::sum);
    }
}
