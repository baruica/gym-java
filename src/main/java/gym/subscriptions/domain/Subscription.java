package gym.subscriptions.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Subscription {

    public final SubscriptionId id;
    public final LocalDate startDate;
    public final Integer price;
    private final List<Period> periods = new ArrayList<>();

    private final List<SubscriptionEvent> raisedEvents = new ArrayList<>();

    public Subscription(SubscriptionId id, LocalDate startDate, Integer planPrice, Integer planDurationInMonths, Boolean isStudent, String email) {
        this.id = id;
        this.startDate = startDate;

        this.price = new Price(planPrice).afterDiscount(planDurationInMonths, isStudent);

        this.periods.add(
            new Period(startDate, planDurationInMonths)
        );

        raisedEvents.add(
            new NewSubscription(
                this.id.toString(),
                this.startDate.toString(),
                email
            )
        );
    }

    public List<SubscriptionEvent> getRaisedEvents() {
        return raisedEvents;
    }

    public void renew() {
        var oldEndOfSubscription = lastPeriod().endDate;

        periods.add(
            lastPeriod().next()
        );

        raisedEvents.add(
            new SubscriptionRenewed(
                id.toString(),
                oldEndOfSubscription.toString(),
                lastPeriod().endDate.toString()
            )
        );
    }

    public Boolean isOngoing(final LocalDate date) {
        return lastPeriod().contains(date);
    }

    public Boolean willBeEnded(final LocalDate asFrom) {
        return lastPeriod().isBefore(asFrom);
    }

    public Double monthlyTurnover() {
        return (double) (price / periods.get(0).durationInMonths);
    }

    private Period lastPeriod() {
        return periods.get(periods.size() - 1);
    }
}
