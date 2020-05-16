package gym.subscriptions.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class Subscription {

    public final SubscriptionId id;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final Integer planDurationInMonths;
    public final Integer price;

    private final List<SubscriptionEvent> raisedEvents = new ArrayList<>();

    public Subscription(SubscriptionId id, LocalDate startDate, Integer planPrice, Integer planDurationInMonths, Boolean isStudent, String email) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = startDate.plus(planDurationInMonths, ChronoUnit.MONTHS).minusDays(1);
        this.planDurationInMonths = planDurationInMonths;

        this.price = new Price(planPrice).afterDiscount(planDurationInMonths, isStudent);

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
        var oldEndDate = this.endDate;

        this.endDate = oldEndDate.plus(planDurationInMonths, ChronoUnit.MONTHS);

        raisedEvents.add(
            new SubscriptionRenewed(
                id.toString(),
                oldEndDate.toString(),
                this.endDate.toString()
            )
        );
    }

    public Boolean willBeEndedAfter(final LocalDate asFrom) {
        return asFrom.isAfter(endDate);
    }

    public Boolean isOngoing(final LocalDate date) {
        return (startDate.isEqual(date) || startDate.isBefore(date))
            && (endDate.isEqual(date) || endDate.isAfter(date));
    }

    public Double monthlyTurnover() {
        return (double) (price / planDurationInMonths);
    }
}
