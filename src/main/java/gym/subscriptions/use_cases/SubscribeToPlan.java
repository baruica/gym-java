package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

record SubscribeToPlan(
    SubscriptionRepository subscriptionRepository
) {
    public Subscription handle(SubscribeToPlanCommand command) {

        var subscription = Subscription.subscribe(
            command.subscriptionId(),
            LocalDate.parse(command.startDate()),
            command.planDurationInMonths(),
            command.planPrice(),
            command.isStudent()
        );

        subscriptionRepository.store(subscription);

        return subscription;
    }
}
