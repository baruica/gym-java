package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionId;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenewSubscriptionsAutomaticallyTest {

    @Test
    public void handle() {

        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var subscription = Subscription.subscribe(
            new SubscriptionId(subscriptionRepository.nextId()),
            LocalDate.parse("2018-06-09"),
            1, 200,
            false,
            "bob@gmail.com"
        );
        subscriptionRepository.store(subscription);

        var tested = new RenewSubscriptionsAutomatically(subscriptionRepository);

        var renewedSubscriptions = tested.handle(
            new RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        );

        assertEquals("2018-08-08", renewedSubscriptions.get(renewedSubscriptions.size() - 1).endDate.toString());
    }
}
