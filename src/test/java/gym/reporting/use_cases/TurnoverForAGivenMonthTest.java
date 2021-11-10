package gym.reporting.use_cases;

import gym.reporting.Turnover;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;
import gym.subscriptions.use_cases.InMemorySubscriptionRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnoverForAGivenMonthTest {

    @Test
    public void turnover_for_a_given_month_with_ongoing_subscriptions() {

        SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();

        var today = LocalDate.parse("2018-06-09");
        var inAMonth = LocalDate.parse("2018-07-09");

        subscriptionRepository.store(
            Subscription.subscribe(
                subscriptionRepository.nextId(),
                today,
                1,
                50,
                false
            )
        );
        subscriptionRepository.store(
            Subscription.subscribe(
                subscriptionRepository.nextId(),
                today,
                12,
                400,
                false
            )
        );
        subscriptionRepository.store(
            Subscription.subscribe(
                subscriptionRepository.nextId(),
                inAMonth,
                12,
                500,
                false
            )
        );

        var tested = new TurnoverForAGivenMonth(
            subscriptionRepository
        );

        assertEquals(2, subscriptionRepository.ongoingSubscriptions(today).size());
        assertEquals(new Turnover(80), tested.handle(new TurnoverForAGivenMonth.TurnoverForAGivenMonthQuery("2018-06-09")));

        assertEquals(3, subscriptionRepository.ongoingSubscriptions(inAMonth).size());
        assertEquals(new Turnover(118), tested.handle(new TurnoverForAGivenMonth.TurnoverForAGivenMonthQuery("2018-07-09")));
    }
}
