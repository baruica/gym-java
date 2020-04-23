package gym.subscriptions.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Subscription {

    public final SubscriptionId id;
    private final ChosenPlan chosenPlan;
    public final LocalDate startDate;
    public final Integer price;
    private final List<Period> periods = new ArrayList<>();

    public Subscription(SubscriptionId id, LocalDate startDate, String planId, Integer planPrice, Integer planDurationInMonths, Boolean isStudent) {
        this.id = id;
        this.startDate = startDate;
        this.chosenPlan = new ChosenPlan(planId, planPrice, planDurationInMonths);

        this.price = new Price(chosenPlan.price).afterDiscount(chosenPlan.isYearly(), isStudent);

        this.periods.add(
            new Period(startDate, chosenPlan.durationInMonths)
        );
    }

    public Double monthlyTurnover() {
        if (this.chosenPlan.isYearly()) {
            return (double) price / 12;
        }
        return (double) price;
    }

    private Period lastPeriod() {
        return periods.get(periods.size() - 1);
    }

    public Boolean isOngoing(final LocalDate date) {
        return lastPeriod().contains(date);
    }

    public Boolean willBeEnded(final LocalDate asFrom) {
        return lastPeriod().isBefore(asFrom);
    }

    public void renew() {
        periods.add(
            lastPeriod().next()
        );
    }
}
