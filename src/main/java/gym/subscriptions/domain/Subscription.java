package gym.subscriptions.domain;

import common.Aggregate;
import common.AggregateId;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class Subscription implements Aggregate {

    public final SubscriptionId id;
    private final Integer planDurationInMonths;
    public final LocalDate startDate;
    public LocalDate endDate;
    public final Price price;

    private Subscription(SubscriptionId id, Integer planDurationInMonths, LocalDate startDate, Price price) {
        this.id = id;
        this.planDurationInMonths = planDurationInMonths;
        this.startDate = startDate;
        this.endDate = this.startDate.plus(this.planDurationInMonths, ChronoUnit.MONTHS).minusDays(1);
        this.price = price;
    }

    @Override
    public AggregateId id() {
        return id;
    }

    public static Subscription subscribe(
        SubscriptionId id,
        LocalDate startDate,
        Integer planDurationInMonths,
        Integer planPrice,
        Boolean isStudent,
        String email
    ) {
        return new Subscription(
            id,
            planDurationInMonths,
            startDate,
            new Price(planPrice).afterDiscount(new Discount(planDurationInMonths, isStudent))
        );
    }

    public void renew() {
        var oldEndDate = this.endDate;
        this.endDate = oldEndDate.plus(planDurationInMonths, ChronoUnit.MONTHS);
    }

    public Boolean willBeEndedAfter(final LocalDate asFrom) {
        return asFrom.isAfter(endDate);
    }

    public Boolean isOngoing(final LocalDate date) {
        return (startDate.isEqual(date) || startDate.isBefore(date))
            && (endDate.isEqual(date) || endDate.isAfter(date));
    }

    public Double monthlyTurnover() {
        return (double) (price.amount / planDurationInMonths);
    }

    public static final class Price {

        public final Integer amount;

        Price(Integer amount) {
            this.amount = amount;
        }

        Price afterDiscount(Discount discount) {
            return new Price((int) (amount * (1 - discount.rate)));
        }

        @Override
        public String toString() {
            return String.valueOf(amount.intValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Price price = (Price) o;
            return Objects.equals(amount, price.amount);
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }
    }

    private static final class Discount {

        private Double rate = 0.0;

        Discount(final Integer durationInMonths, final Boolean isStudent) {
            if (durationInMonths == 12) {
                rate += 0.3;
            }
            if (isStudent) {
                rate += 0.2;
            }
        }
    }
}
