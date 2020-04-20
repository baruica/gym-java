package gym.reporting.use_cases;

import gym.plans.domain.PlanId;
import gym.subscriptions.domain.ChosenPlan;
import gym.subscriptions.domain.Subscription;
import gym.subscriptions.infrastructure.SubscriptionInMemoryRepository;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TurnoverForAGivenMonthTest {

    @Test
    public void turnover_for_a_given_month_with_ongoing_subscriptions() {

        var subscriptionRepository = new SubscriptionInMemoryRepository();

        var today = LocalDate.parse("2018-06-09");
        var inAMonth = LocalDate.parse("2018-07-09");

        subscriptionRepository.store(
            new Subscription(
                subscriptionRepository.nextId(),
                new ChosenPlan(new PlanId("abc"), 50, 1, ""),
                today,
                false
            )
        );
        subscriptionRepository.store(
            new Subscription(
                subscriptionRepository.nextId(),
                new ChosenPlan(new PlanId("def"), 500, 12, ""),
                inAMonth,
                false
            )
        );

        var tested = new TurnoverForAGivenMonth(
            subscriptionRepository
        );

        assertEquals(50, tested.handle(new TurnoverForAGivenMonthQuery(today)), 0);
        assertEquals(1, subscriptionRepository.ongoingSubscriptions(today).size());

        assertEquals(29, tested.handle(new TurnoverForAGivenMonthQuery(inAMonth)), 1);
        assertEquals(1, subscriptionRepository.ongoingSubscriptions(inAMonth).size());
    }
}
