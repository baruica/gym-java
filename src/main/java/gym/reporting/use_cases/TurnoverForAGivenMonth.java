package gym.reporting.use_cases;

import gym.reporting.Turnover;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

public record TurnoverForAGivenMonth(String asOfDate) {
    record Handler(
        SubscriptionRepository subscriptionRepository
    ) {
        Turnover handle(TurnoverForAGivenMonth query) {

            var asOfDate = LocalDate.parse(query.asOfDate());

            return Turnover.monthly(
                subscriptionRepository.ongoingSubscriptions(asOfDate)
            );
        }
    }
}
