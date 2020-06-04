package gym.subscriptions.domain;

import common.Aggregate;
import common.AggregateId;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class Subscription implements Aggregate {

    public final SubscriptionId id;
    private final Integer durationInMonths;
    private final LocalDate startDate;
    public LocalDate endDate;
    public final Price price;

    private Subscription(SubscriptionId id, Integer planDurationInMonths, LocalDate startDate, LocalDate endDate, Price price) {
        this.id = id;
        this.durationInMonths = planDurationInMonths;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    @Override
    public AggregateId id() {
        return id;
    }

    public static Subscription subscribe(
        String id,
        LocalDate startDate,
        Integer planDurationInMonths,
        Integer planPrice,
        Boolean isStudent,
        String email
    ) throws SubscriptionException {
        return new Subscription(
            new SubscriptionId(id),
            planDurationInMonths,
            startDate,
            startDate.plus(planDurationInMonths, ChronoUnit.MONTHS).minusDays(1),
            new Price(planPrice).afterDiscount(new Discount(planDurationInMonths, isStudent))
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

    public static final class Price {

        public final Integer amount;

        Price(Integer amount) throws SubscriptionException {
            if (amount < 0) {
                throw new SubscriptionException("Price amount must be non-negative, was [" + amount + "]");
            }
            this.amount = amount;
        }

        Price afterDiscount(Discount discount) throws SubscriptionException {
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

        Discount(final int durationInMonths, final boolean isStudent) {
            if (durationInMonths == 12) {
                rate += 0.3;
            }
            if (isStudent) {
                rate += 0.2;
            }
        }
    }
}
