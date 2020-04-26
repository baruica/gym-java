package gym.subscriptions.use_cases;

import gym.subscriptions.domain.PlanSubscribed;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;

final class SubscribeToPlan {

    private final SubscriptionRepository subscriptionRepository;

    SubscribeToPlan(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public PlanSubscribed handle(SubscribeToPlanCommand command) {

        Subscription subscription = new Subscription(
            subscriptionRepository.nextId(),
            LocalDate.parse(command.startDate),
            command.planPrice,
            command.planDurationInMonths,
            command.isStudent,
            command.email
        );

        subscriptionRepository.store(subscription);

        return new PlanSubscribed(
            subscription.id,
            subscription.startDate,
            command.email
        );
    }
}
