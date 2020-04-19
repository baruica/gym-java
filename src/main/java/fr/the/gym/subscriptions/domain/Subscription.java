package fr.the.gym.subscriptions.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Subscription {

    private final SubscriptionId id;
    private final ChosenPlan chosenPlan;
    private final LocalDate startDate;
    private final Integer price;
    private final List<Period> periods = new ArrayList<>();

    public Subscription(SubscriptionId id, ChosenPlan chosenPlan, LocalDate startDate, Boolean isStudent) {
        this.id = id;
        this.chosenPlan = chosenPlan;
        this.startDate = startDate;

        this.price = new Price(chosenPlan.price()).afterDiscount(chosenPlan.isYearly(), isStudent);

        this.periods.add(
            new Period(startDate, chosenPlan.durationInMonths())
        );
    }

    public SubscriptionId id() {
        return id;
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
        return lastPeriod().beforeDate(asFrom);
    }

    public void renew() {
        periods.add(
            lastPeriod().next()
        );
    }

    public LocalDate startDate() {
        return startDate;
    }

    public Integer price() {
        return price;
    }
}
