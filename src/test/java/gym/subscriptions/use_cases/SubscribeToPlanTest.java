package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscribeToPlanTest {

    @Test
    public void handle() {
        var repository = new InMemorySubscriptionRepository();
        var subscriptionId = repository.nextId();

        var tested = new SubscribeToPlan.Handler(repository);

        var subscription = tested.handle(
            new SubscribeToPlan(
                subscriptionId,
                500,
                12,
                LocalDate.parse("2018-12-18"),
                false
            )
        );

        assertEquals(new Subscription.SubscriptionId(subscriptionId), subscription.id);
        assertEquals(450, subscription.price.amount());
    }
}
