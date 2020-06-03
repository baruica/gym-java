package gym.subscriptions.use_cases;

import gym.subscriptions.domain.SubscriptionException;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscribeToPlanTest {

    @Test
    public void handle() throws SubscriptionException {
        var repository = new SubscriptionInMemoryRepository();
        var subscriptionId = repository.nextId();

        var tested = new SubscribeToPlan(repository);

        var subscription = tested.handle(
            new SubscribeToPlanCommand(
                subscriptionId,
                500,
                12,
                "2018-12-18",
                false,
                "bob@gmail.com")
        );

        assertEquals(subscriptionId, subscription.id.toString());
        assertEquals(350, subscription.price.amount);
    }
}
