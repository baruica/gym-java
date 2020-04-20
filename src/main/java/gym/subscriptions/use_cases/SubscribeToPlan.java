package gym.subscriptions.use_cases;

import gym.subscriptions.domain.PlanSubscribed;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

final class SubscribeToPlan {

    private final SubscriptionRepository subscriptionRepository;

    SubscribeToPlan(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public PlanSubscribed handle(SubscribeToPlanCommand command) {

        Subscription subscription = new Subscription(
            command.subscriptionId,
            command.chosenPlan,
            command.startDate,
            command.isStudent
        );

        subscriptionRepository.store(subscription);

        return new PlanSubscribed(
            subscription.id,
            subscription.startDate,
            command.email
        );
    }
}
