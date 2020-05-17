package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRenewed;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenewSubscriptionsAutomaticallyTest {

    @Test
    public void handle() {

        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var subscription = new Subscription(
            subscriptionRepository.nextId(),
            LocalDate.parse("2018-06-09"),
            200,
            1,
            false,
            "bob@gmail.com"
        );
        subscriptionRepository.store(subscription);

        var tested = new RenewSubscriptionsAutomatically(subscriptionRepository);

        var events = tested.handle(
            new RenewSubscriptionsAutomaticallyCommand("2018-07-09")
        );

        assertEquals(
            events.get(events.size() - 1),
            new SubscriptionRenewed(
                subscription.id.toString(),
                "2018-07-08",
                "2018-08-08"
            )
        );
    }
}
