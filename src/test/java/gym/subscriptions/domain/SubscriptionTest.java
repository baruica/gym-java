package gym.subscriptions.domain;

import gym.subscriptions.domain.Subscription.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest {

    @Test
    public void base_price_for_monthly_subscription() {
        var subsciptionWithoutDiscount = monthlySubscription(300, LocalDate.parse("2018-06-05"), false);

        assertEquals(new Price(300), subsciptionWithoutDiscount.price);
    }

    @Test
    public void ten_percent_discount_for_yearly_subscriptions() {
        var subscriptionWithYearlyDiscount = yearlySubscription(1000, LocalDate.parse("2018-06-05"), false);

        assertEquals(new Price(900), subscriptionWithYearlyDiscount.price);
    }

    @Test
    public void twenty_percent_discount_for_students() {
        var monthlySubscriptionWithStudentDiscount = monthlySubscription(100, LocalDate.parse("2018-06-05"), true);
        assertEquals(new Price(80), monthlySubscriptionWithStudentDiscount.price);

        var yearlySubscriptionWithStudentDiscount = yearlySubscription(100, LocalDate.parse("2018-06-05"), true);
        assertEquals(new Price(72), yearlySubscriptionWithStudentDiscount.price);
    }

    @Test
    public void five_percent_discount_after_three_years() {
        var subscription = yearlySubscription(1000, LocalDate.parse("2018-06-05"), false);
        assertEquals(new Price(900), subscription.price);

        subscription.renew();
        assertEquals(new Price(900), subscription.price);

        subscription.renew();
        subscription.applyThreeYearsAnniversaryDiscount(LocalDate.parse("2021-06-05"));
        assertEquals(new Price(855), subscription.price);
    }

    @Test
    public void can_be_renewed() {
        var subscription = monthlySubscription(100, LocalDate.parse("2018-06-05"), false);

        assertFalse(subscription.willBeEndedAfter(LocalDate.parse("2018-07-05")));
        assertTrue(subscription.willBeEndedAfter(LocalDate.parse("2018-07-06")));

        subscription.renew();

        assertFalse(subscription.willBeEndedAfter(LocalDate.parse("2018-08-05")));
        assertTrue(subscription.willBeEndedAfter(LocalDate.parse("2018-08-06")));
    }

    @Test
    public void can_be_ongoing() {
        Subscription monthlySubscription = monthlySubscription(100, LocalDate.parse("2018-06-05"), false);

        assertFalse(monthlySubscription.isOngoing(LocalDate.parse("2018-06-04")));
        assertTrue(monthlySubscription.isOngoing(LocalDate.parse("2018-06-05")));
        assertTrue(monthlySubscription.isOngoing(LocalDate.parse("2018-06-19")));
        assertTrue(monthlySubscription.isOngoing(LocalDate.parse("2018-07-05")));
        assertFalse(monthlySubscription.isOngoing(LocalDate.parse("2018-07-06")));
    }

    @Test
    public void can_tell_if_it_will_be_ended_at_a_given_date() {
        var subscriptionEndingEndOfJune = monthlySubscription(100, LocalDate.parse("2018-06-05"), false);

        assertFalse(subscriptionEndingEndOfJune.willBeEndedAfter(LocalDate.parse("2018-07-05")));
        assertTrue(subscriptionEndingEndOfJune.willBeEndedAfter(LocalDate.parse("2018-07-06")));
    }

    @Test
    public void has_a_monthly_turnover() {
        var monthlySubscription = monthlySubscription(100, LocalDate.parse("2018-06-05"), false);
        assertEquals(100, monthlySubscription.monthlyTurnover(), 0);

        var yearlySubscription = yearlySubscription(1200, LocalDate.parse("2018-06-05"), false);
        assertEquals(90, yearlySubscription.monthlyTurnover(), 0);
    }

    private Subscription monthlySubscription(Integer basePrice, LocalDate startDate, Boolean isStudent) {
        return newSubscription(basePrice, 1, startDate, isStudent);
    }

    private Subscription yearlySubscription(Integer basePrice, LocalDate startDate, Boolean isStudent) {
        return newSubscription(basePrice, 12, startDate, isStudent);
    }

    private Subscription newSubscription(Integer basePrice, Integer durationInMonths, LocalDate startDate, Boolean isStudent) {
        return Subscription.subscribe(
            UUID.randomUUID().toString(),
            startDate,
            durationInMonths, basePrice,
            isStudent
        );
    }
}
