package gym.subscriptions.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Subscription {

    public final SubscriptionId id;
    private final Integer durationInMonths;
    private final LocalDate startDate;
    public LocalDate endDate;
    public final Price price;

    public static record SubscriptionId(String id) {
    }

    private Subscription(SubscriptionId id, Integer planDurationInMonths, LocalDate startDate, LocalDate endDate, Price price) {
        this.id = id;
        this.durationInMonths = planDurationInMonths;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public static Subscription subscribe(
        String id,
        LocalDate startDate,
        Integer planDurationInMonths,
        Integer planPrice,
        Boolean isStudent
    ) {
        return new Subscription(
            new SubscriptionId(id),
            planDurationInMonths,
            startDate,
            startDate.plus(planDurationInMonths, ChronoUnit.MONTHS).minusDays(1),
            new Price(planPrice).afterDiscount(planDurationInMonths, isStudent)
        );
    }

    public void renew() {
        var oldEndDate = this.endDate;
        this.endDate = oldEndDate.plus(durationInMonths, ChronoUnit.MONTHS);
    }

    public Boolean willBeEndedAfter(final LocalDate asFrom) {
        return asFrom.isAfter(endDate);
    }

    public Boolean isOngoing(final LocalDate date) {
        return (startDate.isEqual(date) || startDate.isBefore(date))
            && (endDate.isEqual(date) || endDate.isAfter(date));
    }

    public int monthlyTurnover() {
        return (int) (price.amount / (double) durationInMonths);
    }

    public record Price(Integer amount) {

        public Price {
            if (amount < 0) {
                throw new IllegalArgumentException("Price amount must be non-negative, was [" + amount + "]");
            }
        }

        Price afterDiscount(Integer durationInMonths, Boolean isStudent) {
            var rate = 0.0;

            if (durationInMonths == 12) {
                rate += 0.3;
            }
            if (isStudent) {
                rate += 0.2;
            }
            return new Price((int) (amount * (1 - rate)));
        }
    }
}
