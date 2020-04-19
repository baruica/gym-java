package fr.the.gym.reporting.use_cases;

import fr.the.gym.subscriptions.domain.Subscription;
import fr.the.gym.subscriptions.domain.SubscriptionRepository;

final class TurnoverForAGivenMonth {

    private final SubscriptionRepository subscriptionRepository;

    TurnoverForAGivenMonth(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    Double handle(TurnoverForAGivenMonthQuery query) {

        var turnoverForGivenMonth = 0.0;

        for (Subscription ongoingSubscription : subscriptionRepository.ongoingSubscriptions(query.asOfDate).values()) {
            turnoverForGivenMonth += ongoingSubscription.monthlyTurnover();
        }

        return turnoverForGivenMonth;
    }
}
