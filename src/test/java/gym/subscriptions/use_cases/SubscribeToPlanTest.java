package gym.subscriptions.use_cases;

import gym.subscriptions.domain.SubscriptionId;
import gym.subscriptions.domain.SubscriptionRepositoryException;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubscribeToPlanTest {

    @Test
    public void handle() throws SubscriptionRepositoryException {
        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var tested = new SubscribeToPlan(subscriptionRepository);

        var event = tested.handle(
            new SubscribeToPlanCommand(
                "abc",
                500,
                12,
                "2018-12-18",
                false,
                "bob@mail.com"
            )
        );

        assertEquals(350, (int) subscriptionRepository.get(new SubscriptionId(event.subscriptionId)).price);
    }
}
