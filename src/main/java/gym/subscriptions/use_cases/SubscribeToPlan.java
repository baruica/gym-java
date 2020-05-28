package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionEvent;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

final class SubscribeToPlan {

    private final SubscriptionRepository subscriptionRepository;

    SubscribeToPlan(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<SubscriptionEvent> handle(SubscribeToPlanCommand command) {

        var subscription = Subscription.subscribe(
            subscriptionRepository.nextId(),
            LocalDate.parse(command.startDate),
            command.planDurationInMonths, command.planPrice,
            command.isStudent,
            command.email
        );

        subscriptionRepository.store(subscription);

        return subscription.getRaisedEvents();
    }
}
