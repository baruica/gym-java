package gym.reporting.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TurnoverForAGivenMonthTest {

    @Test
    public void turnover_for_a_given_month_with_ongoing_subscriptions() {

        var subscriptionRepository = new SubscriptionInMemoryRepository();

        String todayStr = "2018-06-09";
        var today = LocalDate.parse(todayStr);
        String inAMonthStr = "2018-07-09";
        var inAMonth = LocalDate.parse(inAMonthStr);

        subscriptionRepository.store(
            new Subscription(
                subscriptionRepository.nextId(),
                today,
                "plan abc",
                50,
                1,
                false
            )
        );
        subscriptionRepository.store(
            new Subscription(
                subscriptionRepository.nextId(),
                inAMonth,
                "plan def",
                500,
                12,
                false
            )
        );

        var tested = new TurnoverForAGivenMonth(
            subscriptionRepository
        );

        assertEquals(50, tested.handle(new TurnoverForAGivenMonthQuery(todayStr)), 0);
        assertEquals(1, subscriptionRepository.ongoingSubscriptions(today).size());

        assertEquals(29, tested.handle(new TurnoverForAGivenMonthQuery(inAMonthStr)), 1);
        assertEquals(1, subscriptionRepository.ongoingSubscriptions(inAMonth).size());
    }
}
