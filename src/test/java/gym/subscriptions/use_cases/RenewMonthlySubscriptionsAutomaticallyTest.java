package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenewMonthlySubscriptionsAutomaticallyTest {

    @Test
    public void handle() {

        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var monthlySubscriptionId = subscriptionRepository.nextId();
        subscriptionRepository.store(
            Subscription.subscribe(
                monthlySubscriptionId,
                LocalDate.parse("2018-06-09"),
                1,
                200,
                false
            )
        );

        var yearlySubscriptionId = subscriptionRepository.nextId();
        subscriptionRepository.store(
            Subscription.subscribe(
                yearlySubscriptionId,
                LocalDate.parse("2018-06-09"),
                12,
                1300,
                false
            )
        );

        var tested = new RenewMonthlySubscriptionsAutomatically(subscriptionRepository);

        var renewedSubscriptions = tested.handle(
            new RenewMonthlySubscriptionsAutomaticallyCommand("2018-07-09")
        );

        assertEquals(1, renewedSubscriptions.size());
        assertEquals(
            "2018-08-08",
            renewedSubscriptions.get(renewedSubscriptions.size() - 1).endDate.toString()
        );
    }
}
