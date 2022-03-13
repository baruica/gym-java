package gym.reporting.use_cases;

import gym.reporting.Turnover;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

public record TurnoverForAGivenMonth(LocalDate asOfDate) {
    record Handler(
        SubscriptionRepository subscriptionRepository
    ) {
        Turnover handle(TurnoverForAGivenMonth query) {

            return Turnover.monthly(
                subscriptionRepository.ongoingSubscriptions(query.asOfDate())
            );
        }
    }
}
