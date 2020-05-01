package gym.subscriptions.use_cases;

import gym.subscriptions.domain.NewSubscription;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

final class SubscribeToPlan {

    private final SubscriptionRepository subscriptionRepository;

    SubscribeToPlan(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public NewSubscription handle(SubscribeToPlanCommand command) {

        Subscription subscription = new Subscription(
            subscriptionRepository.nextId(),
            LocalDate.parse(command.startDate),
            command.planPrice,
            command.planDurationInMonths,
            command.isStudent,
            command.email
        );

        subscriptionRepository.store(subscription);

        return new NewSubscription(
            subscription.id,
            subscription.startDate,
            command.email
        );
    }
}
