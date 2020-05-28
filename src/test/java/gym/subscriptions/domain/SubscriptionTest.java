package gym.subscriptions.domain;

import gym.subscriptions.domain.Subscription.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest {

    @Test
    public void base_price_for_monthly_subscription() {
        var subsciptionWithoutDiscount = monthlySubscription(300, fifthOfJune(), false);

        assertEquals(new Price(300), subsciptionWithoutDiscount.price);
    }

    @Test
    public void thirty_percent_discount_for_yearly_subscriptions() {
        var subscriptionWithYearlyDiscount = yearlySubscription(1000, fifthOfJune(), false);

        assertEquals(new Price(700), subscriptionWithYearlyDiscount.price);
    }

    @Test
    public void twenty_percent_discount_for_students() {
        var monthlySubscriptionWithStudentDiscount = monthlySubscription(100, fifthOfJune(), true);
        assertEquals(new Price(80), monthlySubscriptionWithStudentDiscount.price);

        var yearlySubscriptionWithStudentDiscount = yearlySubscription(100, fifthOfJune(), true);
        assertEquals(new Price(50), yearlySubscriptionWithStudentDiscount.price);
    }

    @Test
    public void can_be_renewed() {
        var subscription = monthlySubscription(100, fifthOfJune(), false);

        assertFalse(subscription.willBeEndedAfter(LocalDate.parse("2018-07-04")));
        assertTrue(subscription.willBeEndedAfter(LocalDate.parse("2018-07-05")));

        subscription.renew();

        assertFalse(subscription.willBeEndedAfter(LocalDate.parse("2018-08-04")));
        assertTrue(subscription.willBeEndedAfter(LocalDate.parse("2018-08-05")));
    }

    @Test
    public void can_be_ongoing() {
        Subscription monthlySubscription = monthlySubscription(100, fifthOfJune(), false);

        assertFalse(monthlySubscription.isOngoing(LocalDate.parse("2018-06-04")));
        assertTrue(monthlySubscription.isOngoing(LocalDate.parse("2018-06-05")));
        assertTrue(monthlySubscription.isOngoing(LocalDate.parse("2018-06-19")));
        assertTrue(monthlySubscription.isOngoing(LocalDate.parse("2018-07-04")));
        assertFalse(monthlySubscription.isOngoing(LocalDate.parse("2018-07-05")));
    }

    @Test
    public void can_tell_if_it_will_be_ended_at_a_given_date() {
        var subscriptionEndingEndOfJune = monthlySubscription(100, fifthOfJune(), false);

        assertFalse(subscriptionEndingEndOfJune.willBeEndedAfter(LocalDate.parse("2018-07-04")));
        assertTrue(subscriptionEndingEndOfJune.willBeEndedAfter(LocalDate.parse("2018-07-05")));
    }

    @Test
    public void monthly_turnover() {
        var monthlySubscription = monthlySubscription(100, fifthOfJune(), false);
        assertEquals(100, monthlySubscription.monthlyTurnover(), 0);

        var yearlySubscription = yearlySubscription(1200, fifthOfJune(), false);
        assertEquals(70, yearlySubscription.monthlyTurnover(), 0);
    }

    private LocalDate fifthOfJune() {
        return LocalDate.parse("2018-06-05");
    }

    private Subscription monthlySubscription(Integer basePrice, LocalDate startDate, Boolean isStudent) {
        return newSubscription(basePrice, 1, startDate, isStudent);
    }

    private Subscription yearlySubscription(Integer basePrice, LocalDate startDate, Boolean isStudent) {
        return newSubscription(basePrice, 12, startDate, isStudent);
    }

    private Subscription newSubscription(Integer basePrice, Integer durationInMonths, LocalDate startDate, Boolean isStudent) {
        return Subscription.subscribe(
            new SubscriptionId(UUID.randomUUID().toString()),
            startDate,
            durationInMonths, basePrice,
            isStudent,
            "luke@gmail.com"
        );
    }
}
