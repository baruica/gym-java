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

        var turnoverForGivenMonth = 0.0;
        var asOfDate = LocalDate.parse(query.asOfDate);

        for (Subscription ongoingSubscription : subscriptionRepository.ongoingSubscriptions(asOfDate).values()) {
            turnoverForGivenMonth += ongoingSubscription.monthlyTurnover();
        }

        return turnoverForGivenMonth;
    }
}
