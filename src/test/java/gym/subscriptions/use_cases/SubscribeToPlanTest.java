package gym.subscriptions.use_cases;

import gym.subscriptions.domain.NewSubscription;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscribeToPlanTest {

    @Test
    public void handle() {
        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var tested = new SubscribeToPlan(subscriptionRepository);

        var events = tested.handle(
            new SubscribeToPlanCommand(
                500,
                12,
                "2018-12-18",
                false,
                "bob@gmail.com"
            )
        );

        assertEquals(
            events.get(events.size() - 1),
            new NewSubscription(
                events.get(events.size() - 1).aggregateId(),
                "2018-12-18",
                "bob@gmail.com"
            )
        );
    }
}
