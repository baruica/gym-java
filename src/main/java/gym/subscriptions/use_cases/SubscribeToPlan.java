package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.Subscription.SubscriptionId;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

public record SubscribeToPlan(
    SubscriptionId subscriptionId,
    Integer planPrice,
    Integer planDurationInMonths,
    LocalDate startDate,
    Boolean isStudent
) {
    record Handler(
        SubscriptionRepository subscriptionRepository
    ) {
        public Subscription handle(SubscribeToPlan command) {

            var subscription = Subscription.subscribe(
                command.subscriptionId(),
                command.startDate(),
                command.planDurationInMonths(),
                command.planPrice(),
                command.isStudent()
            );

            subscriptionRepository.store(subscription);

            return subscription;
        }
    }
}
