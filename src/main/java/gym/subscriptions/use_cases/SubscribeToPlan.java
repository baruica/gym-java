package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionException;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

final class SubscribeToPlan {

    private final SubscriptionRepository subscriptionRepository;

    SubscribeToPlan(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription handle(SubscribeToPlanCommand command) throws SubscriptionException {

        var subscription = Subscription.subscribe(
            command.subscriptionId,
            LocalDate.parse(command.startDate),
            command.planDurationInMonths, command.planPrice,
            command.isStudent,
            command.email
        );

        subscriptionRepository.store(subscription);

        return subscription;
    }
}
