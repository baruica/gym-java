package gym.subscriptions.use_cases;

import gym.plans.domain.PlanId;
import gym.subscriptions.domain.ChosenPlan;
import gym.subscriptions.domain.SubscriptionRepositoryException;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class SubscribeToPlanTest {

    @Test
    public void handle() throws SubscriptionRepositoryException {
        var subscriptionRepository = new SubscriptionInMemoryRepository();
        var subscriptionId = subscriptionRepository.nextId();

        var tested = new SubscribeToPlan(subscriptionRepository);

        var event = tested.handle(
            new SubscribeToPlanCommand(
                new ChosenPlan(
                    new PlanId("abc"),
                    500,
                    12,
                    "yearly plan for 500 euros"
                ),
                subscriptionId,
                LocalDate.parse("2018-12-18"),
                false,
                "bob@mail.com"
            )
        );

        assertEquals(subscriptionId, event.subscriptionId);
        assertEquals(350, (int) subscriptionRepository.get(subscriptionId).price);
    }
}
