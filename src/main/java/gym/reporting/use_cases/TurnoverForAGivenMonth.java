package gym.reporting.use_cases;

import gym.reporting.Turnover;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

final class TurnoverForAGivenMonth {

    private final SubscriptionRepository subscriptionRepository;

    TurnoverForAGivenMonth(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    Turnover handle(TurnoverForAGivenMonthQuery query) {

        var asOfDate = LocalDate.parse(query.asOfDate());

        return Turnover.monthly(
            subscriptionRepository.ongoingSubscriptions(asOfDate)
        );
    }
}
