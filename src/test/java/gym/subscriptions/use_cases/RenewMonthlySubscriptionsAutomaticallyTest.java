package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RenewMonthlySubscriptionsAutomaticallyTest {

    @Test
    public void handle() {

        var subscriptionRepository = new InMemorySubscriptionRepository();

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

        var tested = new RenewMonthlySubscriptionsAutomatically.Handler(subscriptionRepository);

        var renewedSubscriptions = tested.handle(
            new RenewMonthlySubscriptionsAutomatically(LocalDate.parse("2018-07-10"))
        );

        assertEquals(1, renewedSubscriptions.size());
        assertEquals(
            "2018-08-09",
            renewedSubscriptions.get(renewedSubscriptions.size() - 1).endDate.toString()
        );
    }
}
