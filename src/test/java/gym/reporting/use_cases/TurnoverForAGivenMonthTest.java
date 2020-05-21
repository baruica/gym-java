package gym.reporting.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnoverForAGivenMonthTest {

    @Test
    public void turnover_for_a_given_month_with_ongoing_subscriptions() {

        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var today = LocalDate.parse("2018-06-09");
        var inAMonth = LocalDate.parse("2018-07-09");

        subscriptionRepository.store(
            new Subscription(
                subscriptionRepository.nextId(),
                today,
                1, 50,
                false,
                "bob@gmail.com"
            )
        );
        subscriptionRepository.store(
            new Subscription(
                subscriptionRepository.nextId(),
                inAMonth,
                12, 500,
                false,
                "bob@gmail.com"
            )
        );

        var tested = new TurnoverForAGivenMonth(
            subscriptionRepository
        );

        assertEquals(50, tested.handle(new TurnoverForAGivenMonthQuery("2018-06-09")), 0);
        assertEquals(1, subscriptionRepository.ongoingSubscriptions(today).size());

        assertEquals(29, tested.handle(new TurnoverForAGivenMonthQuery("2018-07-09")), 1);
        assertEquals(1, subscriptionRepository.ongoingSubscriptions(inAMonth).size());
    }
}
