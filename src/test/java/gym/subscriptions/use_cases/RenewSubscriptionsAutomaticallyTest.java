package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import gym.subscriptions.infrastructure.SubscriptionRepositoryException;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;

public class RenewSubscriptionsAutomaticallyTest {

    @Test
    public void handle() throws SubscriptionRepositoryException {

        var subscriptionRepository = new SubscriptionInMemoryRepository();
        var subscriptionId = subscriptionRepository.nextId();
        var subscription = new Subscription(
            subscriptionId,
            LocalDate.parse("2018-06-09"),
            200,
            1,
            false,
            "bob@gmail.com"
        );
        subscriptionRepository.store(subscription);

        RenewSubscriptionsAutomatically tested = new RenewSubscriptionsAutomatically(subscriptionRepository);

        var event = tested.handle(
            new RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        );

        assertTrue(event.renewedSubscriptions.contains(subscriptionId.toString()));

        var onGoingDateAfterRewewing = LocalDate.parse("2018-08-01");
        assertTrue(
            subscriptionRepository.get(subscription.id).isOngoing(onGoingDateAfterRewewing)
        );

        var dateWillBeEndedAfterRewal = LocalDate.parse("2018-08-10");
        assertTrue(
            subscriptionRepository.get(subscription.id).willBeEnded(dateWillBeEndedAfterRewal)
        );
    }
}
